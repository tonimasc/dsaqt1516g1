package edu.upc.eetac.dsa.videostore.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marc on 16/12/15.
 *
 * Coleccion de peliculas compradas y alquiladas.
 */
public class MoviesCollection {
    private List<Buys> bought = new ArrayList<>();
    private List<Rent> rented  = new ArrayList<>();
}
