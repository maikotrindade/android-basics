package br.trindade.androidbasics.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.Date;

import br.trindade.androidbasics.model.Movie;

/**
 * @author maiko.trindade
 */
public class DBMovies {

    public static final int BOX_OFFICE = 0;
    private MoviesHelper mHelper;
    private SQLiteDatabase mDatabase;

    public DBMovies(Context context) {
        mHelper = new MoviesHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }

    private static class MoviesHelper extends SQLiteOpenHelper {
        public static final String TABLE_BOX_OFFICE = "movies_box_office";
        public static final String COLUMN_UID = "_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_AUDIENCE_SCORE = "audience_score";
        public static final String COLUMN_SYNOPSIS = "synopsis";
        public static final String COLUMN_URL_THUMBNAIL = "url_thumbnail";
        public static final String COLUMN_URL_SELF = "url_self";
        public static final String COLUMN_URL_CAST = "url_cast";
        public static final String COLUMN_URL_REVIEWS = "url_reviews";
        public static final String COLUMN_URL_SIMILAR = "url_similar";

        private static final String CREATE_TABLE_BOX_OFFICE = "CREATE TABLE " + TABLE_BOX_OFFICE + " (" +
                COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_RELEASE_DATE + " INTEGER," +
                COLUMN_AUDIENCE_SCORE + " INTEGER," +
                COLUMN_SYNOPSIS + " TEXT," +
                COLUMN_URL_THUMBNAIL + " TEXT," +
                COLUMN_URL_SELF + " TEXT," +
                COLUMN_URL_CAST + " TEXT," +
                COLUMN_URL_REVIEWS + " TEXT," +
                COLUMN_URL_SIMILAR + " TEXT" +
                ");";

        private static final String DB_NAME = "movies_db";
        private static final int DB_VERSION = 1;
        private Context mContext;

        public MoviesHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE_BOX_OFFICE);
            } catch (SQLiteException exception) {
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(" DROP TABLE " + TABLE_BOX_OFFICE + " IF EXISTS;");
                onCreate(db);
            } catch (SQLiteException exception) {
            }
        }
    }

    public void insertMovies(int table, ArrayList<Movie> listMovies, boolean clearPrevious) {
        if (clearPrevious) {
            deleteMovies(table);
        }

        //create a sql prepared statement
        String sql = "INSERT INTO " + MoviesHelper.TABLE_BOX_OFFICE + " VALUES (?,?,?,?,?,?,?,?,?,?);";
        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();

        for (int index = 0; index < listMovies.size(); index++) {
            Movie currentMovie = listMovies.get(index);
            statement.clearBindings();
            statement.bindString(2, currentMovie.getTitle());
            statement.bindLong(3, currentMovie.getReleaseDateTheater() == null ? -1 : currentMovie.getReleaseDateTheater().getTime());
            statement.bindLong(4, currentMovie.getAudienceScore());
            statement.bindString(5, currentMovie.getSynopsis());
            statement.bindString(6, currentMovie.getUrlThumbnail());
            statement.bindString(7, currentMovie.getUrlSelf());
            statement.bindString(8, currentMovie.getUrlCast());
            statement.bindString(9, currentMovie.getUrlReviews());
            statement.bindString(10, currentMovie.getUrlSimilar());

            statement.execute();
        }
        //set the transaction as successful and end the transaction
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }

    public ArrayList<Movie> readMovies(int table) {
        ArrayList<Movie> listMovies = new ArrayList<>();

        String[] columns = {MoviesHelper.COLUMN_UID,
                MoviesHelper.COLUMN_TITLE,
                MoviesHelper.COLUMN_RELEASE_DATE,
                MoviesHelper.COLUMN_AUDIENCE_SCORE,
                MoviesHelper.COLUMN_SYNOPSIS,
                MoviesHelper.COLUMN_URL_THUMBNAIL,
                MoviesHelper.COLUMN_URL_SELF,
                MoviesHelper.COLUMN_URL_CAST,
                MoviesHelper.COLUMN_URL_REVIEWS,
                MoviesHelper.COLUMN_URL_SIMILAR
        };
        Cursor cursor = mDatabase.query(MoviesHelper.TABLE_BOX_OFFICE, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {

                //create a new movie object and retrieve the data from the cursor to be stored in this movie object
                Movie movie = new Movie();
                //each step is a 2 part process, find the index of the column first, find the data of that column using
                //that index and finally set our blank movie object to contain our data
                movie.setTitle(cursor.getString(cursor.getColumnIndex(MoviesHelper.COLUMN_TITLE)));
                long releaseDateMilliseconds = cursor.getLong(cursor.getColumnIndex(MoviesHelper.COLUMN_RELEASE_DATE));
                movie.setReleaseDateTheater(releaseDateMilliseconds != -1 ? new Date(releaseDateMilliseconds) : null);
                movie.setAudienceScore(cursor.getInt(cursor.getColumnIndex(MoviesHelper.COLUMN_AUDIENCE_SCORE)));
                movie.setSynopsis(cursor.getString(cursor.getColumnIndex(MoviesHelper.COLUMN_SYNOPSIS)));
                movie.setUrlThumbnail(cursor.getString(cursor.getColumnIndex(MoviesHelper.COLUMN_URL_THUMBNAIL)));
                movie.setUrlSelf(cursor.getString(cursor.getColumnIndex(MoviesHelper.COLUMN_URL_SELF)));
                movie.setUrlCast(cursor.getString(cursor.getColumnIndex(MoviesHelper.COLUMN_URL_CAST)));
                movie.setUrlReviews(cursor.getString(cursor.getColumnIndex(MoviesHelper.COLUMN_URL_REVIEWS)));
                movie.setUrlSimilar(cursor.getString(cursor.getColumnIndex(MoviesHelper.COLUMN_URL_SIMILAR)));
                //add the movie to the list of movie objects which we plan to return
                listMovies.add(movie);
            }
            while (cursor.moveToNext());
        }
        return listMovies;
    }

    public void deleteMovies(int table) {
        mDatabase.delete(MoviesHelper.TABLE_BOX_OFFICE, null, null);
    }
}

