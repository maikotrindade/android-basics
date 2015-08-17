package br.trindade.androidbasics.util.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.trindade.androidbasics.model.Movie;
/**
 * @author maiko.trindade
 */
public class JsonParser {

    public static ArrayList<Movie> parseMoviesJSON(JSONObject response) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Movie> listMovies = new ArrayList<>();
        if (response != null && response.length() > 0) {
            try {
                JSONArray arrayMovies = response.getJSONArray(NetworkContract.BoxOfficeKeys.KEY_MOVIES);
                for (int i = 0; i < arrayMovies.length(); i++) {

                    long id = -1;
                    String title = "NA";
                    String releaseDate = "NA";
                    int audienceScore = -1;
                    String synopsis = "NA";
                    String urlThumbnail = "NA";
                    String urlSelf = "NA";
                    String urlCast = "NA";
                    String urlReviews = "NA";
                    String urlSimilar = "NA";
                    int duration = 0;

                    JSONObject currentMovie = arrayMovies.getJSONObject(i);
                    //get the id of the current movie
                    if (JsonUtil.contains(currentMovie, NetworkContract.BoxOfficeKeys.KEY_ID)) {
                        id = currentMovie.getLong(NetworkContract.BoxOfficeKeys.KEY_ID);
                    }
                    //get the title of the current movie
                    if (JsonUtil.contains(currentMovie, NetworkContract.BoxOfficeKeys.KEY_TITLE)) {
                        title = currentMovie.getString(NetworkContract.BoxOfficeKeys.KEY_TITLE);
                    }

                    //get the date in theaters for the current movie
                    if (JsonUtil.contains(currentMovie, NetworkContract.BoxOfficeKeys.KEY_RELEASE_DATES)) {
                        JSONObject objectReleaseDates = currentMovie.getJSONObject(NetworkContract.BoxOfficeKeys.KEY_RELEASE_DATES);

                        if (JsonUtil.contains(objectReleaseDates, NetworkContract.BoxOfficeKeys.KEY_THEATER)) {
                            releaseDate = objectReleaseDates.getString(NetworkContract.BoxOfficeKeys.KEY_THEATER);
                        }
                    }

                    //get the audience score for the current movie

                    if (JsonUtil.contains(currentMovie, NetworkContract.BoxOfficeKeys.KEY_RATINGS)) {
                        JSONObject objectRatings = currentMovie.getJSONObject(NetworkContract.BoxOfficeKeys.KEY_RATINGS);
                        if (JsonUtil.contains(objectRatings, NetworkContract.BoxOfficeKeys.KEY_AUDIENCE_SCORE)) {
                            audienceScore = objectRatings.getInt(NetworkContract.BoxOfficeKeys.KEY_AUDIENCE_SCORE);
                        }
                    }

                    // get the synopsis of the current movie
                    if (JsonUtil.contains(currentMovie, NetworkContract.BoxOfficeKeys.KEY_SYNOPSIS)) {
                        synopsis = currentMovie.getString(NetworkContract.BoxOfficeKeys.KEY_SYNOPSIS);
                    }

                    //get the url for the thumbnail to be displayed inside the current movie result
                    if (JsonUtil.contains(currentMovie, NetworkContract.BoxOfficeKeys.KEY_POSTERS)) {
                        JSONObject objectPosters = currentMovie.getJSONObject(NetworkContract.BoxOfficeKeys.KEY_POSTERS);

                        if (JsonUtil.contains(objectPosters, NetworkContract.BoxOfficeKeys.KEY_THUMBNAIL)) {
                            urlThumbnail = objectPosters.getString(NetworkContract.BoxOfficeKeys.KEY_THUMBNAIL);
                        }
                    }

                    //get the url of the related links
                    if (JsonUtil.contains(currentMovie, NetworkContract.BoxOfficeKeys.KEY_LINKS)) {
                        JSONObject objectLinks = currentMovie.getJSONObject(NetworkContract.BoxOfficeKeys.KEY_LINKS);
                        if (JsonUtil.contains(objectLinks, NetworkContract.BoxOfficeKeys.KEY_SELF)) {
                            urlSelf = objectLinks.getString(NetworkContract.BoxOfficeKeys.KEY_SELF);
                        }
                        if (JsonUtil.contains(objectLinks, NetworkContract.BoxOfficeKeys.KEY_CAST)) {
                            urlCast = objectLinks.getString(NetworkContract.BoxOfficeKeys.KEY_CAST);
                        }
                        if (JsonUtil.contains(objectLinks, NetworkContract.BoxOfficeKeys.KEY_REVIEWS)) {
                            urlReviews = objectLinks.getString(NetworkContract.BoxOfficeKeys.KEY_REVIEWS);
                        }
                        if (JsonUtil.contains(objectLinks, NetworkContract.BoxOfficeKeys.KEY_SIMILAR)) {
                            urlSimilar = objectLinks.getString(NetworkContract.BoxOfficeKeys.KEY_SIMILAR);
                        }
                    }

                    //get the id of the current movie
                    if (JsonUtil.contains(currentMovie, NetworkContract.BoxOfficeKeys.KEY_DURATION)) {
                        duration = currentMovie.getInt(NetworkContract.BoxOfficeKeys.KEY_DURATION);
                    }

                    Movie movie = new Movie();
                    movie.setId(id);
                    movie.setTitle(title);
                    Date date = null;
                    try {
                        date = dateFormat.parse(releaseDate);
                    } catch (ParseException e) {
                        //a parse exception generated here will store null in the release date, be sure to handle it
                    }
                    movie.setReleaseDateTheater(date);
                    movie.setAudienceScore(audienceScore);
                    movie.setSynopsis(synopsis);
                    movie.setUrlThumbnail(urlThumbnail);
                    movie.setUrlSelf(urlSelf);
                    movie.setUrlCast(urlCast);
                    movie.setUrlThumbnail(urlThumbnail);
                    movie.setUrlReviews(urlReviews);
                    movie.setUrlSimilar(urlSimilar);
                    if (id != -1 && !title.equals("NA")) {
                        listMovies.add(movie);
                    }
                }

            } catch (JSONException e) {

            }
        }
        return listMovies;
    }

}
