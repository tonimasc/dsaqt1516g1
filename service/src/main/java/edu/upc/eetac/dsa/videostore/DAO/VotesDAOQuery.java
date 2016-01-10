package edu.upc.eetac.dsa.videostore.DAO;

public interface VotesDAOQuery {
        public final static String GET_VOTOS =  "select idpeli, usuarioid from votos where usuarioid=unhex(?)";
        public final static String POST_VOTOS =  "insert into votos (idpeli, usuarioid) values (UNHEX(?), UNHEX(?))";
        public final static String UPDATE_BALANCE = "update pelicula set votos= votos + 1 where id=unhex(?)";

}
