package edu.upc.eetac.dsa.videostore.DAO;


import edu.upc.eetac.dsa.videostore.entity.Movie;

import java.sql.*;

public class MovieDAOImpl implements MovieDAO {

    @Override
    public Movie createMovie(String title, String genre, String content, int year, String director, String description,
                             int votos, int numdownloads, int temmaxvisual, int pricerent, int pricesell)
            throws SQLException, MovieAlreadyExistsException {
        Connection connection = null;
        PreparedStatement stmt = null;
        String id = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(MovieDAOQuery.UUID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                id = rs.getString(1);
            else
                throw new SQLException();
            stmt = connection.prepareStatement(MovieDAOQuery.CREATE_MOVIE);
            stmt.setString(1, id);
            stmt.setString(2, title);
            stmt.setString(3, content);
            stmt.setInt(4, year);
            stmt.setString(5, director);
            stmt.setString(6, description);
            stmt.setInt(7, votos);
            stmt.setInt(8, numdownloads);
            stmt.setInt(9, temmaxvisual);
            stmt.setInt(10, pricerent);
            stmt.setInt(11, pricesell);
            //el fechainclusion se inserta por propia database: current timestamp
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
        return getMoviebyID(id);
    }

    @Override
    public Movie getMoviebyID(String id) throws SQLException {
        Movie movie = null;

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(MovieDAOQuery.GET_MOVIES_BY_ID);
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getString("id"));
                movie.setTitle(rs.getString("titulo"));
                movie.setGenre(rs.getString("genero"));
                movie.setYear(rs.getInt("ano"));
                movie.setDirector(rs.getString("director"));
                movie.setDescription(rs.getString("descripcion"));
                movie.setVotes(rs.getInt("votos"));
                movie.setNummaxdownloads(rs.getInt("numdescargaspermitidas"));
                movie.setMaxtimeshow(rs.getInt("tiempomaximovisualizacion"));
                movie.setRentcost(rs.getInt("precioalquiler"));
                movie.setRentcost(rs.getInt("preciocompra"));
                movie.setTimeadded(rs.getTimestamp("fechainclusion").getTime());
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return movie;
    }

    /**
    @Override
    public MoviesCollection getMovies(long timestamp, boolean before) throws SQLException {
        MoviesCollection stingCollection = new MoviesCollection();

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            if (before)
                stmt = connection.prepareStatement(MovieDAOQuery.GET_MOVIES);
            else
                stmt = connection.prepareStatement(MovieDAOQuery.GET_STINGS_AFTER);
            stmt.setTimestamp(1, new Timestamp(timestamp));

            ResultSet rs = stmt.executeQuery();
            boolean first = true;
            while (rs.next()) {
                Sting sting = new Sting();
                sting.setId(rs.getString("id"));
                sting.setUserid(rs.getString("userid"));
                sting.setCreator(rs.getString("fullname"));
                sting.setSubject(rs.getString("subject"));
                sting.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
                sting.setLastModified(rs.getTimestamp("last_modified").getTime());
                if (first) {
                    stingCollection.setNewestTimestamp(sting.getLastModified());
                    first = false;
                }
                stingCollection.setOldestTimestamp(sting.getLastModified());
                stingCollection.getStings().add(sting);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }

        return stingCollection;
    }
     */
    @Override
    public Movie updateMovie(String id, String title, String genre, int year, String director, String description,
                             int votos, int numdownloads, int temmaxvisual, int pricerent, int pricesell) throws SQLException {
        Movie movie = null;

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(MovieDAOQuery.UPDATE_STING);
            stmt.setString(1, title);
            stmt.setString(2, genre);
            stmt.setInt(3, year);
            stmt.setString(4, director);
            stmt.setString(5, description);
            stmt.setInt(6, votos);
            stmt.setInt(7, numdownloads);
            stmt.setInt(8, temmaxvisual);
            stmt.setInt(9, pricerent);
            stmt.setInt(10, pricesell);
            stmt.executeUpdate();

            int rows = stmt.executeUpdate();
            if (rows == 1)
                movie = getMoviebyID(id);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }

        return movie;
    }

    @Override
    public boolean deleteMovie(String id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(MovieDAOQuery.DELETE_STING);
            stmt.setString(1, id);

            int rows = stmt.executeUpdate();
            return (rows == 1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
    }
}
