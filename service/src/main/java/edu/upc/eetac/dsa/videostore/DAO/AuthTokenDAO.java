package edu.upc.eetac.dsa.videostore.DAO;

import edu.upc.eetac.dsa.videostore.Auth.UserInfo;
import edu.upc.eetac.dsa.videostore.entity.AuthToken;

import java.sql.SQLException;

public interface AuthTokenDAO {
    public UserInfo getUserByAuthToken(String token) throws SQLException;
    public AuthToken createAuthToken(String userid) throws SQLException;
    public void deleteToken(String userid) throws  SQLException;
}
