package edu.upc.eetac.dsa.videostore.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marc on 16/12/15.
 *
 * Coleccion de peliculas compradas y alquiladas.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)

public class MoviesCollection {
    private List<Movie> moviesList = new ArrayList<>();

    public List<Movie> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }
}
