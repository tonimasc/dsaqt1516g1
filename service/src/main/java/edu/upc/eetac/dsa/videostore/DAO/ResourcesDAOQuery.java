package edu.upc.eetac.dsa.videostore.DAO;

public interface ResourcesDAOQuery {
    public final static String GET_RES =  "select hex(peliculaid) as peliculaid, recursopeli, recursoportada from recursos where peliculaid=unhex(?)";
    public final static String POST_RES =  "insert into recursos (peliculaid, recursopeli, recursoportada) values (UNHEX(?), ?, ?)";
    public final static String UPDATE_RES = "update recursos set recursopeli=?, recursoportada=? where peliculaid=unhex(?)";
    public final static String DELETE_RES = "delete from recursos where peliculaid=unhex(?)";
}
