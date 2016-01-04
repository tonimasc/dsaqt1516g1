package edu.upc.eetac.dsa.videostore.DAO;

import edu.upc.eetac.dsa.videostore.entity.User;

import java.sql.SQLException;

public interface UserDAO {
    public User createUser(String loginid, String password, String email, int balance) throws SQLException, UserAlreadyExistsException;

    public User updateProfile(String id, String loginid, String email, int balance) throws SQLException;

    public User getUserById(String id) throws SQLException;

    public User getUserByLoginid(String loginid) throws SQLException;

    public boolean deleteUser(String id) throws SQLException;

    public boolean checkPassword(String id, String password) throws SQLException;
}
