package edu.upc.eetac.dsa.videostore;


import edu.upc.eetac.dsa.videostore.DAO.AuthTokenDAOImpl;
import edu.upc.eetac.dsa.videostore.DAO.UserAlreadyExistsException;
import edu.upc.eetac.dsa.videostore.DAO.UserDAO;
import edu.upc.eetac.dsa.videostore.DAO.UserDAOImpl;
import edu.upc.eetac.dsa.videostore.entity.AuthToken;
import edu.upc.eetac.dsa.videostore.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

@Path("users")
public class UserResource {
    @Context
    private SecurityContext securityContext;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(VideostoreMediaType.VIDEOSTORE_AUTH_TOKEN)
    public Response registerUser(@FormParam("loginid") String loginid, @FormParam("password") String password, @FormParam("email") String email, @Context UriInfo uriInfo) throws URISyntaxException {
        if(loginid == null || password == null || email == null)
            throw new BadRequestException("all parameters are mandatory");
        UserDAO userDAO = new UserDAOImpl();
        User user = null;
        AuthToken authToken = null;
        int saldo = 20;
        try{
            //Incluimos un saldo de 20 puntos
            user = userDAO.createUser(loginid, password, email, saldo);
            authToken = (new AuthTokenDAOImpl()).createAuthToken(user.getId());
        }catch (UserAlreadyExistsException e){
            throw new WebApplicationException("loginid already exists", Response.Status.CONFLICT);
        }catch(SQLException e){
            throw new InternalServerErrorException();
        }
        URI uri = new URI(uriInfo.getAbsolutePath().toString() + "/" + user.getId());
        return Response.created(uri).type(VideostoreMediaType.VIDEOSTORE_AUTH_TOKEN).entity(authToken).build();
    }

    @Path("/{id}")
    @GET
    @Produces(VideostoreMediaType.VIDEOSTORE_USER)
    public User getUser(@PathParam("id") String id) {
        User user = null;
        try {
            user = (new UserDAOImpl()).getUserById(id);
        } catch (SQLException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
        if(user == null)
            throw new NotFoundException("User with id = "+id+" doesn't exist");
        return user;
    }

    @Path("/{id}")
    @PUT
    @Consumes(VideostoreMediaType.VIDEOSTORE_USER)
    @Produces(VideostoreMediaType.VIDEOSTORE_USER)
    public User updateUser(@PathParam("id") String id, User user) {
        if(user == null)
            throw new BadRequestException("entity is null");
        if(!id.equals(user.getId()))
            throw new BadRequestException("path parameter id and entity parameter id doesn't match");

        String userid = securityContext.getUserPrincipal().getName();
        if(!userid.equals(id))
            throw new ForbiddenException("operation not allowed");

        UserDAO userDAO = new UserDAOImpl();
        try {
           user = userDAO.updateProfile(userid, user.getLoginid(), user.getEmail(), user.getBalance());
            if(user == null)
                throw new NotFoundException("User with id = "+id+" doesn't exist");
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        return user;
    }

    @Path("/{id}")
    @DELETE
    public void deleteUser(@PathParam("id") String id){
        String userid = securityContext.getUserPrincipal().getName();
        if(!userid.equals(id))
            throw new ForbiddenException("operation not allowed");
        UserDAO userDAO = new UserDAOImpl();
        try {
            if(!userDAO.deleteUser(id))
                throw new NotFoundException("User with id = "+id+" doesn't exist");
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
    }

}
