package edu.upc.eetac.dsa.videostore.DAO;


public interface MovieDAOQuery {
    public final static String UUID = "select REPLACE(UUID(),'-','')";
    public final static String CREATE_MOVIE = "insert into pelicula (id, titulo, genero, ano, director, descripcion, votos, numdescargaspermitidas, tiempomaximovisualizacion, precioalquiler, preciocompra, recursoportada) values (UNHEX(?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public final static String GET_MOVIES_BY_ID = "select hex(id) as id, titulo, genero, ano, director, descripcion, votos, numdescargaspermitidas, tiempomaximovisualizacion, precioalquiler, preciocompra, fechainclusion, recursoportada from pelicula where id=unhex(?)";
    public final static String GET_MOVIES_BY_YEAR = "select hex(id) as id, titulo, genero, ano, director, descripcion, votos, numdescargaspermitidas, tiempomaximovisualizacion, precioalquiler, preciocompra, fechainclusion, recursoportada from pelicula where ano=? order by fechainclusion DESC";
    public final static String GET_MOVIES_BY_TITLE = "select hex(id) as id, titulo, genero, ano, director, descripcion, votos, numdescargaspermitidas, tiempomaximovisualizacion, precioalquiler, preciocompra, fechainclusion, recursoportada from pelicula where titulo=? order by ano DESC";
    public final static String GET_MOVIES_BY_DIRECTOR = "select hex(id) as id, titulo, genero, ano, director, descripcion, votos, numdescargaspermitidas, tiempomaximovisualizacion, precioalquiler, preciocompra, fechainclusion, recursoportada from pelicula where director=? order by ano DESC";
    public final static String GET_MOVIES_BY_VOTES = "select hex(id) as id, titulo, genero, ano, director, descripcion, votos, numdescargaspermitidas, tiempomaximovisualizacion, precioalquiler, preciocompra, fechainclusion, recursoportada from pelicula order by votos DESC";
    public final static String GET_MOVIES_BY_LASTADDED = "select hex(id) as id, titulo, genero, ano, director, descripcion, votos, numdescargaspermitidas, tiempomaximovisualizacion, precioalquiler, preciocompra, fechainclusion,recursoportada from pelicula order by fechainclusion DESC";
    public final static String GET_MOVIES_BY_DEST = "select hex(id) as id, titulo, genero, ano, director, descripcion, votos, numdescargaspermitidas, tiempomaximovisualizacion, precioalquiler, preciocompra, fechainclusion, recursoportada from pelicula order by fechainclusion DESC, votos DESC";
    public final static String UPDATE_MOVIE = "update pelicula set titulo=?, genero=?, ano=?, director=?, descripcion=?, votos=?, numdescargaspermitidas=?, tiempomaximovisualizacion=?, precioalquiler=?, preciocompra=?, recursoportada=? where id=unhex(?)";
    public final static String DELETE_MOVIE = "delete from pelicula where id=unhex(?)";
}
