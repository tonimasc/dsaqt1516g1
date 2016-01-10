package edu.upc.eetac.dsa.videostore.DAO;

import java.sql.SQLException;

public interface VotesDAO {
    public boolean sumitVote(String userid, String movieid) throws SQLException;
}
