package edu.upc.eetac.dsa.videostore.DAO;


import edu.upc.eetac.dsa.videostore.db.Database;
import edu.upc.eetac.dsa.videostore.entity.Resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourcesDAOImpl implements ResourcesDAO {
    @Override
    public Resources createResource(String peliculaid, String recursopeli, String recursoportada) throws SQLException, ResourcesAlreadyExistsException{
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(ResourcesDAOQuery.POST_RES);
            stmt.setString(1, peliculaid);
            stmt.setString(2, recursopeli);
            stmt.setString(3, recursoportada);
            stmt.executeQuery();

        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) {
                connection.close();
            }
        }
        return getResource(peliculaid);
    }
    @Override
    public Resources updateResource(String peliculaid, String recursopeli, String recursoportada) throws SQLException{
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            Resources resources = new Resources();
            connection = Database.getConnection();
            stmt = connection.prepareStatement(ResourcesDAOQuery.UPDATE_RES);

            stmt.setString(1, recursopeli);
            stmt.setString(2, recursoportada);
            stmt.setString(3, peliculaid);
            stmt.executeUpdate();

            int rows = stmt.executeUpdate();
            if (rows == 1)
                resources = getResource(peliculaid);
                return resources;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
    }
    @Override
    public Resources getResource(String peliculaid) throws SQLException{
        Connection connection = null;
        PreparedStatement stmt = null;
        Resources resources = new Resources();
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(ResourcesDAOQuery.GET_RES);
            stmt.setString(1, peliculaid);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                resources.setIdmovie(rs.getString("peliculaid"));
                resources.setResourcesmovie(rs.getString("recursopeli"));
                resources.setResourcescover(rs.getString("recursoportada"));

            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return resources;
    }
    @Override
    public boolean deleteUser(String peliculaid) throws SQLException{
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(ResourcesDAOQuery.DELETE_RES);
            stmt.setString(1, peliculaid);

            int rows = stmt.executeUpdate();
            return (rows == 1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
    }


}

