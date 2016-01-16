package edu.upc.eetac.dsa.videostore.DAO;

import edu.upc.eetac.dsa.videostore.entity.Buys;
import edu.upc.eetac.dsa.videostore.entity.MoviesUser;
import edu.upc.eetac.dsa.videostore.entity.Rent;

import java.sql.SQLException;

public interface OperationDAO {
    public Buys createBuy(String userid, String idmovie, int numdescargas) throws SQLException, MovieAlreadyExistsException;
    public boolean updateBuy(String userid, String idmovie, int numdescargas) throws SQLException;
    public Buys getBuyByIDmovieandUser(String userid, String idmovie) throws SQLException;
    public boolean deleteBuy(String userid, String idmovie) throws SQLException;
    public boolean alreadyBuy (String userid, String idmovie) throws SQLException;
    public Rent createRent(String userid, String idmovie, int horasrestantes) throws SQLException, MovieAlreadyExistsException;
    public boolean updateRent(String userid, String idmovie, int horasrestantes) throws SQLException;
    public Rent getRentByIDmovieandUser(String userid, String idmovie) throws SQLException;
    public boolean deleteRent(String userid, String idmovie) throws SQLException;
    public boolean alreadyRent (String userid, String idmovie) throws SQLException;
    public MoviesUser getOperationbyID_Collection(String userid) throws SQLException;

}
