package edu.upc.eetac.dsa.videostore;

import edu.upc.eetac.dsa.videostore.DAO.*;
import edu.upc.eetac.dsa.videostore.entity.Buys;
import edu.upc.eetac.dsa.videostore.entity.Movie;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.sql.SQLException;

@Path("comprar")
public class BuyResource {
    @Context
    private SecurityContext securityContext;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(VideostoreMediaType.VIDEOSTORE_BUY)
    public Buys doBuy (@FormParam("idusuario") String iduser, @FormParam("idmovie") String idmovie) {
        if(iduser == null || idmovie == null)
            throw new BadRequestException("all parameters are mandatory");

        String userid = securityContext.getUserPrincipal().getName();
        if(!userid.equals(iduser))
            throw new ForbiddenException("operation not allowed");

        OperationDAO operationDAO = new OperationDAOImpl();
        UserDAO userDAO = new UserDAOImpl();
        MovieDAO movieDAO = new MovieDAOImpl();
        Buys buys;
        try {
            if (!userDAO.checkBalance(iduser))
                throw new BadRequestException("User with id = " + iduser + " insuficient founds");
            if(!operationDAO.alreadyBuy(iduser, idmovie))
            {
                //Consulto la pelicula
                Movie movie = movieDAO.getMoviebyID(idmovie);
                //Actualizo el saldo del usuario
                userDAO.updateBalance(iduser, -movie.getBuycost());
                //Creo una entrada en la tabla compradas.
                buys = operationDAO.createBuy(iduser, idmovie, movie.getNummaxdownloads());
            }
            else
                throw new BadRequestException("Movie with id = " + idmovie + " already buyed");

            return buys;

        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        catch (MovieAlreadyExistsException e){
            throw new InternalServerErrorException();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(VideostoreMediaType.VIDEOSTORE_BUY)
    public Buys updateBuy(@FormParam("idusuario") String iduser, @FormParam("idmovie") String idmovie, @FormParam("numdescargas") int num) {
        if(iduser == null || idmovie == null || num == 0)
            throw new BadRequestException("all parameters are mandatory");

        boolean admin = securityContext.isUserInRole("admin");
        if (!admin)
            throw new ForbiddenException("operation not allowed");

        try{
            Buys buys = null;
            OperationDAO operationDAO = new OperationDAOImpl();
            boolean update = operationDAO.updateBuy(iduser, idmovie, num);
            if(!update)
                throw new InternalServerErrorException();
            buys = operationDAO.getBuyByIDmovieandUser(iduser,idmovie);
            return buys;
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }

    }

    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void deleteBuy(@FormParam("idusuario") String iduser, @FormParam("idmovie") String idmovie){
        if(iduser == null || idmovie == null)
            throw new BadRequestException("all parameters are mandatory");

        OperationDAO operationDAO = new OperationDAOImpl();
        try {
            boolean admin = securityContext.isUserInRole("admin");
            if (admin)
            {
                if(!operationDAO.deleteBuy(iduser, idmovie))
                    throw new NotFoundException("Buy doesn't exist");;
            }
            else
                throw new ForbiddenException("operation not allowed");

        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
    }

    @GET
    @Path("/{idusuario}/{idmovie}")
    @Produces(VideostoreMediaType.VIDEOSTORE_BUY)
    public Buys getBuy(@PathParam("idusuario") String iduser, @PathParam("idmovie") String idmovie) {
        if(iduser == null || idmovie == null)
            throw new BadRequestException("all parameters are mandatory");

        boolean admin = securityContext.isUserInRole("admin");
        if (!admin)
            throw new ForbiddenException("operation not allowed");

        try{
            Buys buys = null;
            OperationDAO operationDAO = new OperationDAOImpl();
            buys = operationDAO.getBuyByIDmovieandUser(iduser, idmovie);
            if(buys == null)
                throw new InternalServerErrorException();
            return buys;
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }

    }
}
