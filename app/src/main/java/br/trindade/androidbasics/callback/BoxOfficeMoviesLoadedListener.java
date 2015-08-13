package br.trindade.androidbasics.callback;

import java.util.ArrayList;

import br.trindade.androidbasics.model.Movie;

/**
 * @author maiko.trindade
 */
public interface BoxOfficeMoviesLoadedListener {
    public void onBoxOfficeMoviesLoaded(ArrayList<Movie> listMovies);
}
