package edu.upc.eetac.dsa.videostore;

import edu.upc.eetac.dsa.videostore.DAO.*;
import edu.upc.eetac.dsa.videostore.entity.Movie;
import edu.upc.eetac.dsa.videostore.entity.Rent;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.sql.SQLException;

@Path("alquiler")
public class RentResource {
    @Context
    private SecurityContext securityContext;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(VideostoreMediaType.VIDEOSTORE_RENT)
    public Rent doRent (@FormParam("idusuario") String iduser, @FormParam("idmovie") String idmovie) {
        if(iduser == null || idmovie == null)
            throw new BadRequestException("all parameters are mandatory");

        String userid = securityContext.getUserPrincipal().getName();
        if(!userid.equals(iduser))
            throw new ForbiddenException("operation not allowed");

        OperationDAO operationDAO = new OperationDAOImpl();
        UserDAO userDAO = new UserDAOImpl();
        MovieDAO movieDAO = new MovieDAOImpl();
        Rent rent;
        try {
            if (!userDAO.checkBalance(iduser))
                throw new BadRequestException("User with id = " + iduser + " insuficient founds");
            if(!operationDAO.alreadyRent(iduser, idmovie))
            {
                //Consulto la pelicula
                Movie movie = movieDAO.getMoviebyID(idmovie);
                //Actualizo el saldo del usuario
                userDAO.updateBalance(iduser, -movie.getRentcost());
                //Creo una entrada en la tabla compradas.
                rent = operationDAO.createRent(iduser, idmovie, movie.getMaxtimeshow());
            }
            else
                throw new BadRequestException("Movie with id = " + idmovie + " already rent");

            return rent;

        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        catch (MovieAlreadyExistsException e){
            throw new BadRequestException("Movie with id = " + idmovie + " already rent");
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(VideostoreMediaType.VIDEOSTORE_RENT)
    public Rent updateRent(@FormParam("idusuario") String iduser, @FormParam("idmovie") String idmovie, @FormParam("horasrestantes") int hres) {
        if(iduser == null || idmovie == null || hres == 0)
            throw new BadRequestException("all parameters are mandatory");

        boolean admin = securityContext.isUserInRole("admin");
        if (!admin)
            throw new ForbiddenException("operation not allowed");

        try{
            Rent rent = null;
            OperationDAO operationDAO = new OperationDAOImpl();
            boolean update = operationDAO.updateRent(iduser, idmovie, hres);
            if(!update)
                throw new InternalServerErrorException();
            rent = operationDAO.getRentByIDmovieandUser(iduser,idmovie);
            return rent;
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
    }

    @Path("/{idusuario}/{idmovie}")
    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void deleteBuy(@PathParam("idusuario") String iduser, @PathParam("idmovie") String idmovie){
        if(iduser == null || idmovie == null)
            throw new BadRequestException("all parameters are mandatory");

        OperationDAO operationDAO = new OperationDAOImpl();
        try {
            boolean admin = securityContext.isUserInRole("admin");
            if (admin)
            {
                boolean estado = operationDAO.deleteRent(iduser, idmovie);
                if(!estado)
                    throw new NotFoundException("Rent doesn't exist");
            }
            else
                throw new ForbiddenException("operation not allowed");

        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
    }

    @Path("/{idusuario}/{idmovie}")
    @GET
    @Produces(VideostoreMediaType.VIDEOSTORE_BUY)
    public Rent getRent(@PathParam("idusuario") String iduser, @PathParam("idmovie") String idmovie) {
        if(iduser == null || idmovie == null)
            throw new BadRequestException("all parameters are mandatory");

        boolean admin = securityContext.isUserInRole("admin");
        if (!admin)
            throw new ForbiddenException("operation not allowed");

        try{
            Rent rent = null;
            OperationDAO operationDAO = new OperationDAOImpl();
            rent = operationDAO.getRentByIDmovieandUser(iduser, idmovie);
            if(rent == null)
                throw new NotFoundException("Rent doesn't exist");
            return rent;
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }

    }
}
