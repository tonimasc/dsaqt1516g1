package edu.upc.eetac.dsa.videostore.DAO;

import edu.upc.eetac.dsa.videostore.entity.Resources;

import java.sql.SQLException;

public interface ResourcesDAO {
    public Resources createResource(String peliculaid, String recursopeli) throws SQLException, ResourcesAlreadyExistsException;
    public Resources updateResource(String peliculaid, String recursopeli) throws SQLException;
    public Resources getResource(String peliculaid) throws SQLException;
    public boolean deleteResource(String peliculaid) throws SQLException;
}
