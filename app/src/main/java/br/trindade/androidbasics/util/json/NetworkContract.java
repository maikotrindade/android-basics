package br.trindade.androidbasics.util.json;

/**
 * @author maiko.trindade
 */
public interface NetworkContract {

     class UrlEndpoints {
        public static final String URL_BOX_OFFICE = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json";
        public static final String URL_CHAR_QUESTION = "?";
        public static final String URL_CHAR_AMEPERSAND = "&";
        public static final String URL_PARAM_API_KEY = "apikey=";
        public static final String URL_PARAM_LIMIT = "limit=";
    }

    class BoxOfficeKeys {
        public static final String KEY_MOVIES = "movies";
        public static final String KEY_ID = "id";
        public static final String KEY_TITLE = "title";
        public static final String KEY_RELEASE_DATES = "release_dates";
        public static final String KEY_THEATER = "theater";
        public static final String KEY_RATINGS = "ratings";
        public static final String KEY_AUDIENCE_SCORE = "audience_score";
        public static final String KEY_SYNOPSIS = "synopsis";
        public static final String KEY_POSTERS = "posters";
        public static final String KEY_THUMBNAIL = "thumbnail";
        public static final String KEY_LINKS = "links";
        public static final String KEY_SELF = "self";
        public static final String KEY_CAST = "cast";
        public static final String KEY_REVIEWS = "reviews";
        public static final String KEY_SIMILAR = "similar";
        public static final String KEY_DURATION = "runtime";
    }
}