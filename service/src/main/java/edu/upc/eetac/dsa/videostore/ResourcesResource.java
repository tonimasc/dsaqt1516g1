package edu.upc.eetac.dsa.videostore;

import edu.upc.eetac.dsa.videostore.DAO.ResourcesAlreadyExistsException;
import edu.upc.eetac.dsa.videostore.DAO.ResourcesDAO;
import edu.upc.eetac.dsa.videostore.DAO.ResourcesDAOImpl;
import edu.upc.eetac.dsa.videostore.entity.Resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

@Path("resources")
public class ResourcesResource {
    @Context
    private SecurityContext securityContext;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(VideostoreMediaType.VIDEOSTORE_RESOURCES)
    public Response createMovie(@FormParam("peliculaid") String peliculaid, @FormParam("recursopeli") String recursopeli, @Context UriInfo uriInfo)
            throws URISyntaxException {

        boolean admin = securityContext.isUserInRole("admin");
        if(!admin)
            throw new ForbiddenException("operation not allowed");

        if (peliculaid == null || recursopeli == null)
            throw new BadRequestException("all parameters are mandatory");

        ResourcesDAO resourcesDAO = new ResourcesDAOImpl();
        Resources resources = null;
        try {
            resources = resourcesDAO.createResource(peliculaid, recursopeli);
        }
        catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        catch (ResourcesAlreadyExistsException e) {
            throw new WebApplicationException("Resource already exists", Response.Status.CONFLICT);
        }
        URI uri = new URI(uriInfo.getAbsolutePath().toString() + "/" + resources.getIdmovie());
        return Response.created(uri).type(VideostoreMediaType.VIDEOSTORE_RESOURCES).entity(resources).build();
    }

    @Path("/{peliculaid}")
    @PUT
    @Consumes(VideostoreMediaType.VIDEOSTORE_RESOURCES)
    @Produces(VideostoreMediaType.VIDEOSTORE_RESOURCES)
    public Resources updateMovie(@PathParam("peliculaid") String idpelicula, Resources resources) {
        if (resources == null)
            throw new BadRequestException("entity is null");
        if (!idpelicula.equals(resources.getIdmovie()))
            throw new BadRequestException("path parameter id and entity parameter id doesn't match");

        boolean admin = securityContext.isUserInRole("admin");
        if(!admin)
            throw new ForbiddenException("operation not allowed");
        ResourcesDAO resourcesDAO = new ResourcesDAOImpl();
        try {
            resources = resourcesDAO.updateResource(resources.getIdmovie(),resources.getResourcesmovie());
            if (resources == null)
                throw new NotFoundException("Movie with id = " + idpelicula + " doesn't exist");
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        return resources;
    }
    @Path("/{peliculaid}")
    @DELETE
    public void deleteMovie(@PathParam("peliculaid") String idpelicula) {
        ResourcesDAO resourcesDAO = new ResourcesDAOImpl();
        boolean admin = securityContext.isUserInRole("admin");
        if(!admin)
            throw new ForbiddenException("operation not allowed");
        try {
            if (!resourcesDAO.deleteResource(idpelicula))
                throw new NotFoundException("Movie with id = " + idpelicula + " doesn't exist");
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
    }
}
