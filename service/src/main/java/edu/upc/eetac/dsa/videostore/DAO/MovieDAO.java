package edu.upc.eetac.dsa.videostore.DAO;


import edu.upc.eetac.dsa.videostore.entity.Movie;
import edu.upc.eetac.dsa.videostore.entity.MoviesCollection;

import java.sql.SQLException;

public interface MovieDAO {
    public Movie createMovie(String title, String genre, int year, String director, String description,
                             int votos, int numdownloads, int temmaxvisual, int pricerent, int pricesell, String recursoportada) throws SQLException, MovieAlreadyExistsException;
    public Movie getMoviebyID(String id) throws SQLException;
    public MoviesCollection getMoviesbyYEAR(int year) throws SQLException;
    public MoviesCollection getMoviesbyTITLE(String titulo) throws SQLException;
    public MoviesCollection getMoviesbyDIRECTOR(String director) throws SQLException;
    public MoviesCollection getMoviesbyLASTADDED() throws SQLException;
    public MoviesCollection getMoviesbyVOTES() throws SQLException;
    public MoviesCollection getMoviesbyDEST() throws SQLException;
    public Movie updateMovie(String id, String title, String genre, int year, String director, String description,
                             int votos, int numdownloads, int temmaxvisual, int pricerent, int pricesell, String recursoportada) throws SQLException;
    public boolean deleteMovie(String id) throws SQLException;

}
