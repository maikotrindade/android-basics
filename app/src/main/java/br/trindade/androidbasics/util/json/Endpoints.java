package br.trindade.androidbasics.util.json;

import br.trindade.androidbasics.util.BasicsApplication;

/**
 * @author maiko.trindade
 */
public class Endpoints {
    public static String getRequestUrlBoxOfficeMovies(int limit) {

        return NetworkContract.UrlEndpoints.URL_BOX_OFFICE
                + NetworkContract.UrlEndpoints.URL_CHAR_QUESTION
                + NetworkContract.UrlEndpoints.URL_PARAM_API_KEY + BasicsApplication.API_KEY_ROTTEN_TOMATOES
                + NetworkContract.UrlEndpoints.URL_CHAR_AMEPERSAND
                + NetworkContract.UrlEndpoints.URL_PARAM_LIMIT + limit;
    }

    public static String getRequestUrlUpcomingMovies(int limit) {

        return NetworkContract.UrlEndpoints.URL_UPCOMING
                + NetworkContract.UrlEndpoints.URL_CHAR_QUESTION
                + NetworkContract.UrlEndpoints.URL_PARAM_API_KEY + BasicsApplication.API_KEY_ROTTEN_TOMATOES
                + NetworkContract.UrlEndpoints.URL_CHAR_AMEPERSAND
                + NetworkContract.UrlEndpoints.URL_PARAM_LIMIT + limit;
    }
}
