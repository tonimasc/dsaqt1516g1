package edu.upc.eetac.dsa.videostore.DAO;


import edu.upc.eetac.dsa.videostore.entity.Movie;

import java.sql.SQLException;

public interface MovieDAO {
    public Movie createMovie(String title, String genre, String content, int year, String director, String description,
                             int votos, int numdownloads, int temmaxvisual, int pricerent, int pricesell) throws SQLException, MovieAlreadyExistsException;
    public Movie getMoviebyID(String id) throws SQLException;
    //public MoviesCollection getMovies(long timestamp, boolean before) throws SQLException;
    public Movie updateMovie(String id, String title, String genre, int year, String director, String description,
                             int votos, int numdownloads, int temmaxvisual, int pricerent, int pricesell) throws SQLException;
    public boolean deleteMovie(String id) throws SQLException;
}
