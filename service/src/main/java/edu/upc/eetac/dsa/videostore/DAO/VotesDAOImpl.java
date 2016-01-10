package edu.upc.eetac.dsa.videostore.DAO;

import edu.upc.eetac.dsa.videostore.db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VotesDAOImpl implements VotesDAO {

    @Override
    public boolean sumitVote(String userid, String movieid) throws SQLException{
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(VotesDAOQuery.GET_VOTOS);
            stmt.setString(1, userid);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                connection.setAutoCommit(false);
                stmt.close();

                stmt = connection.prepareStatement(VotesDAOQuery.UPDATE_BALANCE);
                stmt.setString(1, movieid);
                int rows = stmt.executeUpdate();
                connection.commit();

                stmt = connection.prepareStatement(VotesDAOQuery.POST_VOTOS);
                stmt.setString(1, movieid);
                stmt.setString(2, userid);
                stmt.executeUpdate();
                connection.commit();

                return (rows == 1);
            }
            else
                return false;

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
}
