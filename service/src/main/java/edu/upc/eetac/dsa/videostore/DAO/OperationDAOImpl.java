package edu.upc.eetac.dsa.videostore.DAO;

import edu.upc.eetac.dsa.videostore.db.Database;
import edu.upc.eetac.dsa.videostore.entity.Buys;
import edu.upc.eetac.dsa.videostore.entity.MoviesUser;
import edu.upc.eetac.dsa.videostore.entity.Rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperationDAOImpl implements OperationDAO {
    @Override
    public Buys createBuy(String userid, String idmovie, int numdescargas) throws SQLException, MovieAlreadyExistsException {
        Buys buy = null;

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            if (alreadyBuy(userid, idmovie))
                throw new MovieAlreadyExistsException();
            connection = Database.getConnection();

            stmt = connection.prepareStatement(OperationDAOQuery.CREATE_COMPRA);
            stmt.setString(1, userid);
            stmt.setString(2, idmovie);
            stmt.setInt(3, numdescargas);

            int rows = stmt.executeUpdate();
            if (rows == 1)
                buy = getBuyByIDmovieandUser(userid, idmovie);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }

        return buy;
    }
    @Override
    public boolean updateBuy(String userid, String idmovie, int numdescargas) throws SQLException{

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(OperationDAOQuery.UPDATE_COMPRA);
            stmt.setInt(1, numdescargas);
            stmt.setString(2, userid);
            stmt.setString(3, idmovie);

            int e = stmt.executeUpdate();
            if (e == 0){
                return true;
            }
            else
                return false;

        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
    }
    @Override
    public boolean deleteBuy(String userid, String idmovie) throws SQLException{
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(OperationDAOQuery.DELETE_COMPRA);
            stmt.setString(1, userid);
            stmt.setString(1, idmovie);

            int rows = stmt.executeUpdate();
            return (rows == 1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
    }
    @Override
    public Buys getBuyByIDmovieandUser(String userid, String idmovie) throws SQLException{

        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            Buys buys = new Buys();
            // Obtiene la conexión del DataSource
            connection = Database.getConnection();

            // Prepara la consulta
            stmt = connection.prepareStatement(OperationDAOQuery.GET_COMPRA_BY_BOTH_ID);
            // Da valor a los parámetros de la consulta
            stmt.setString(1, userid);
            stmt.setString(2, idmovie);

            // Ejecuta la consulta
            ResultSet rs = stmt.executeQuery();
            // Procesa los resultados
            while (rs.next()) {
                String usuarioid = rs.getString("usuarioid");
                buys.setUserid(usuarioid);
                buys.setMovieid(rs.getString("peliculaid"));
                buys.setDowmloadedtimes(rs.getInt("numdescargas"));
                buys.setDatebuy(rs.getTimestamp("fechacompra").getTime());
            }

            return buys;
        } catch (SQLException e) {
            // Relanza la excepción
            throw e;
        } finally {
            // Libera la conexión
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
    }
    @Override
    public boolean alreadyBuy (String userid, String idmovie) throws SQLException{
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(OperationDAOQuery.GET_COMPRA_BY_BOTH_ID);
            stmt.setString(1, userid);
            stmt.setString(2, idmovie);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return false;
            }
            else
                return true;

        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }
    @Override
    public Rent createRent(String userid, String idmovie, int horasrestantes) throws SQLException, MovieAlreadyExistsException {
        Rent rent = null;

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            if (alreadyBuy(userid, idmovie))
                throw new MovieAlreadyExistsException();
            connection = Database.getConnection();

            stmt = connection.prepareStatement(OperationDAOQuery.CREATE_RENT);
            stmt.setString(1, userid);
            stmt.setString(2, idmovie);
            stmt.setInt(3, horasrestantes);

            int rows = stmt.executeUpdate();
            if (rows == 1)
                rent = getRentByIDmovieandUser(userid, idmovie);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }

        return rent;
    }
    @Override
    public Rent updateRent(String userid, String idmovie, int horasrestantes) throws SQLException{


        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            Rent rent = null;
            connection = Database.getConnection();

            stmt = connection.prepareStatement(OperationDAOQuery.UPDATE_RENT);
            stmt.setInt(1, horasrestantes);
            stmt.setString(2, userid);
            stmt.setString(3, idmovie);

            int rows = stmt.executeUpdate();
            if (rows == 1)
                rent = getRentByIDmovieandUser(userid, idmovie);
            return rent;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }


    }
    @Override
    public boolean deleteRent(String userid, String idmovie) throws SQLException{
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(OperationDAOQuery.DELETE_RENT);
            stmt.setString(1, userid);
            stmt.setString(1, idmovie);

            int rows = stmt.executeUpdate();
            return (rows == 1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
    }
    @Override
    public Rent getRentByIDmovieandUser(String userid, String idmovie) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            Rent rent = new Rent();
            // Obtiene la conexión del DataSource
            connection = Database.getConnection();

            // Prepara la consulta
            stmt = connection.prepareStatement(OperationDAOQuery.GET_RENT_BY_BOTH_ID);
            // Da valor a los parámetros de la consulta
            stmt.setString(1, userid);
            stmt.setString(2, idmovie);

            // Ejecuta la consulta
            ResultSet rs = stmt.executeQuery();
            // Procesa los resultados
            while(rs.next()) {
                String usuarioid = rs.getString("usuarioid");
                rent.setUserid(usuarioid);
                rent.setMovieid(rs.getString("peliculaid"));
                rent.setViewtimes(rs.getInt("horasrestantes"));
                rent.setDaterent(rs.getTimestamp("fechacompra").getTime());
            }
            return rent;
        } catch (SQLException e) {
            // Relanza la excepción
            throw e;
        } finally {
            // Libera la conexión
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
    }
    @Override
    public boolean alreadyRent(String userid, String idmovie) throws SQLException{
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(OperationDAOQuery.GET_RENT_BY_BOTH_ID);
            stmt.setString(1, userid);
            stmt.setString(2, idmovie);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return false;
            }
            else
                return true;

        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }
    @Override
    public MoviesUser getOperationbyID_Collection(String userid) throws SQLException{
        MoviesUser moviesUser = new MoviesUser();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = Database.getConnection();
            stmt = connection.prepareStatement(OperationDAOQuery.GET_COMPRA_BY_ID);
            stmt.setString(1, userid);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Buys buys = new Buys();
                buys.setUserid(rs.getString("usuarioid"));
                buys.setMovieid(rs.getString("peliculaid"));
                buys.setDowmloadedtimes(rs.getInt("numdescargas"));
                buys.setDatebuy(rs.getTimestamp("fechacompra").getTime());

                moviesUser.getBought().add(buys);
            }

            connection.setAutoCommit(false);
            stmt.close();
            stmt = connection.prepareStatement(OperationDAOQuery.GET_RENT_BY_ID);
            stmt.setString(1, userid);
            rs = stmt.executeQuery();
            connection.commit();

            while (rs.next()) {
                Rent rent = new Rent();
                rent.setUserid(rs.getString("usuarioid"));
                rent.setMovieid(rs.getString("peliculaid"));
                rent.setViewtimes(rs.getInt("horasrestantes"));
                rent.setDaterent(rs.getTimestamp("fechacompra").getTime());

                moviesUser.getRented().add(rent);
            }



        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }

        return moviesUser;
    }

}
