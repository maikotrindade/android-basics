package br.trindade.androidbasics.util.json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.trindade.androidbasics.model.Movie;
import static br.trindade.androidbasics.util.json.NetworkContract.BoxOfficeKeys.*;

/**
 * @author maiko.trindade
 */
public class JsonParser {

    private static final String TAG = JsonParser.class.getSimpleName();

    public static ArrayList<Movie> parseMoviesJSON(JSONObject response) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Movie> listMovies = new ArrayList<>();
        if (response != null && response.length() > 0) {
            try {
                JSONArray arrayMovies = response.getJSONArray(KEY_MOVIES);
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
                    if (JsonUtil.contains(currentMovie, KEY_ID)) {
                        id = currentMovie.getLong(KEY_ID);
                    }
                    //get the title of the current movie
                    if (JsonUtil.contains(currentMovie, KEY_TITLE)) {
                        title = currentMovie.getString(KEY_TITLE);
                    }

                    //get the date in theaters for the current movie
                    if (JsonUtil.contains(currentMovie, KEY_RELEASE_DATES)) {
                        JSONObject objectReleaseDates = currentMovie.getJSONObject(KEY_RELEASE_DATES);

                        if (JsonUtil.contains(objectReleaseDates, KEY_THEATER)) {
                            releaseDate = objectReleaseDates.getString(KEY_THEATER);
                        }
                    }

                    //get the audience score for the current movie
                    if (JsonUtil.contains(currentMovie, KEY_RATINGS)) {
                        JSONObject objectRatings = currentMovie.getJSONObject(KEY_RATINGS);
                        if (JsonUtil.contains(objectRatings, KEY_AUDIENCE_SCORE)) {
                            audienceScore = objectRatings.getInt(KEY_AUDIENCE_SCORE);
                        }
                    }

                    // get the synopsis of the current movie
                    if (JsonUtil.contains(currentMovie, KEY_SYNOPSIS)) {
                        synopsis = currentMovie.getString(KEY_SYNOPSIS);
                    }

                    //get the url for the thumbnail to be displayed inside the current movie result
                    if (JsonUtil.contains(currentMovie, KEY_POSTERS)) {
                        JSONObject objectPosters = currentMovie.getJSONObject(KEY_POSTERS);

                        if (JsonUtil.contains(objectPosters, KEY_THUMBNAIL)) {
                            urlThumbnail = objectPosters.getString(KEY_THUMBNAIL);
                        }
                    }

                    //get the url of the related links
                    if (JsonUtil.contains(currentMovie, KEY_LINKS)) {
                        JSONObject objectLinks = currentMovie.getJSONObject(KEY_LINKS);
                        if (JsonUtil.contains(objectLinks, KEY_SELF)) {
                            urlSelf = objectLinks.getString(KEY_SELF);
                        }
                        if (JsonUtil.contains(objectLinks, KEY_CAST)) {
                            urlCast = objectLinks.getString(KEY_CAST);
                        }
                        if (JsonUtil.contains(objectLinks, KEY_REVIEWS)) {
                            urlReviews = objectLinks.getString(KEY_REVIEWS);
                        }
                        if (JsonUtil.contains(objectLinks, KEY_SIMILAR)) {
                            urlSimilar = objectLinks.getString(KEY_SIMILAR);
                        }
                    }

                    //get the id of the current movie
                    if (JsonUtil.contains(currentMovie, KEY_DURATION)) {
                        duration = currentMovie.getInt(KEY_DURATION);
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
                    movie.setRuntime(duration);
                    if (id != -1 && !title.equals("NA")) {
                        listMovies.add(movie);
                    }
                }

            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return listMovies;
    }
}
