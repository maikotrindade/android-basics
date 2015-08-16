package br.trindade.androidbasics.util;

import com.android.volley.RequestQueue;

import org.json.JSONObject;

import java.util.ArrayList;

import br.trindade.androidbasics.database.DBMovies;
import br.trindade.androidbasics.model.Movie;
import br.trindade.androidbasics.util.json.Endpoints;
import br.trindade.androidbasics.util.json.JsonParser;
import br.trindade.androidbasics.util.json.Requestor;

/**
 * @author maiko.trindade
 */
public class MovieUtils {
    public static ArrayList<Movie> loadBoxOfficeMovies(RequestQueue requestQueue) {
        JSONObject response = Requestor.requestMoviesJSON(requestQueue, Endpoints.getRequestUrlBoxOfficeMovies(30));
        ArrayList<Movie> listMovies = JsonParser.parseMoviesJSON(response);
        BasicsApplication.getWritableDatabase().insertMovies(DBMovies.BOX_OFFICE, listMovies, true);
        return listMovies;
    }
}
