package edu.upc.eetac.dsa.videostore.DAO;

public interface AuthTokenDAOQuery {
    public final static String UUID = "select REPLACE(UUID(),'-','')";
    public final static String CREATE_TOKEN = "insert into auth_tokens (usuarioid, token) values (UNHEX(?), UNHEX(?))";
    public final static String GET_USER_BY_TOKEN = "select hex(u.id) as id from usuario u, auth_tokens t where t.token=unhex(?) and u.id=t.usuarioid";
    public final static String GET_ROLES_OF_USER = "select hex(usuarioid), rol from user_roles where usuarioid=unhex(?)";
    public final static String DELETE_TOKEN = "delete from auth_tokens where usuarioid = unhex(?)";
}
