package edu.upc.eetac.dsa.videostore.DAO;

public interface UserDAOQuery {
    public final static String UUID = "select REPLACE(UUID(),'-','')";
    public final static String CREATE_USER = "insert into usuario (id, loginid, password, email, saldo) values (UNHEX(?), ?, UNHEX(MD5(?)), ?, ?)";
    public final static String UPDATE_USER = "update usuario set loginid=?, password=?, email=?, balance=? where id=unhex(?)";
    public final static String ASSIGN_ROLE_REGISTERED = "insert into usuario_roles (usuarioid, role) values (UNHEX(?), 'registered')";
    public final static String GET_USER_BY_ID = "select hex(u.id) as id, u.loginid, u.email, u.fullname, u.saldo from usuario u where id=unhex(?)";
    public final static String GET_USER_BY_USERNAME = "select hex(u.id) as id, u.loginid, u.email, u.fullname, u.saldo from usuario u where u.loginid=?";
    public final static String DELETE_USER = "delete from usuario where id=unhex(?)";
    public final static String GET_PASSWORD =  "select hex(password) as password from usuario where id=unhex(?)";
}
