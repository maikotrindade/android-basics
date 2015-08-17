package br.trindade.androidbasics.util.json;

import br.trindade.androidbasics.util.BasicsApplication;
import static br.trindade.androidbasics.util.json.NetworkContract.UrlEndpoints.*;

/**
 * @author maiko.trindade
 */
public class Endpoints {
    public static String getRequestUrlBoxOfficeMovies(int limit) {

        return URL_BOX_OFFICE
                + URL_CHAR_QUESTION
                + URL_PARAM_API_KEY + BasicsApplication.API_KEY_ROTTEN_TOMATOES
                + URL_CHAR_AMEPERSAND
                + URL_PARAM_LIMIT + limit;
    }
}
