package edu.upc.eetac.dsa.videostore.DAO;

/**
 * Created by sergio on 9/09/15.
 */
public class MovieDAOQuery {
    public final static String UUID = "select REPLACE(UUID(),'-','')";
    public final static String CREATE_MOVIE = "insert into pelicula (id, titulo, genero, ano, director, descripcion, votos, numdescargaspermitidas, tiempomaximovisualizacion, precioalquiler, preciocompra) values (UNHEX(?), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public final static String GET_MOVIES_BY_ID = "select hex(id) as id, titulo, genero, ano, director, descripcion, votos, numdescargaspermitidas, tiempomaximovisualizacion, precioalquiler, preciocompra, fechainclusion from pelicula where id=unhex(?)";
    //public final static String GET_MOVIES = "select hex(s.id) as id, hex(s.userid) as userid, s.subject, s.creation_timestamp, s.last_modified, u.fullname from stings s, users u where creation_timestamp < ? and u.id=s.userid order by creation_timestamp desc limit 25";
    //public final static String GET_STINGS_AFTER = "select hex(s.id) as id, hex(s.userid) as userid, s.subject, s.creation_timestamp, s.last_modified, u.fullname from stings s, users u  where creation_timestamp > ? and u.id=s.userid order by creation_timestamp desc limit 25";
    public final static String UPDATE_STING = "update peliculas set titulo=?, genero=?, ano=?, director=?, descripcion=?, votos=?, numdescargaspermitidas=?, tiempomaximovisualizacion=?, precioalquiler=?, preciocompra=? where id=unhex(?) ";
    public final static String DELETE_STING = "delete from peliculas where id=unhex(?)";
}
