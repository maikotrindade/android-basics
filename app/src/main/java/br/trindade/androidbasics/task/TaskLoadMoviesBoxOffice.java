package br.trindade.androidbasics.task;

import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import br.trindade.androidbasics.callback.NetworkErrorListener;
import br.trindade.androidbasics.callback.BoxOfficeMoviesLoadedListener;
import br.trindade.androidbasics.database.DBMovies;
import br.trindade.androidbasics.model.Movie;
import br.trindade.androidbasics.network.VolleySingleton;
import br.trindade.androidbasics.util.BasicsApplication;
import br.trindade.androidbasics.util.json.Endpoints;
import br.trindade.androidbasics.util.json.JsonParser;

/**
 * @author maiko.trindade
 */
public class TaskLoadMoviesBoxOffice extends AsyncTask<Void, Void, ArrayList<Movie>> {

    public static final int MOVIES_LIMIT = 50;

    private BoxOfficeMoviesLoadedListener moviesListener;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private NetworkErrorListener<Object> callback;


    public TaskLoadMoviesBoxOffice(BoxOfficeMoviesLoadedListener moviesListener,
                                   NetworkErrorListener<Object> callback) {
        this.moviesListener = moviesListener;
        this.callback = callback;

        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }


    @Override
    protected ArrayList<Movie> doInBackground(Void... params) {
        JSONObject response = requestMoviesJSON(requestQueue,
                Endpoints.getRequestUrlBoxOfficeMovies(MOVIES_LIMIT));
        ArrayList<Movie> listMovies = JsonParser.parseMoviesJSON(response);
        BasicsApplication.getWritableDatabase().insertMovies(DBMovies.BOX_OFFICE, listMovies, true);
        return listMovies;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> listMovies) {
        if (moviesListener != null) {
            moviesListener.onBoxOfficeMoviesLoaded(listMovies);
        }
    }

    private JSONObject requestMoviesJSON(RequestQueue requestQueue, String url) {
        JSONObject response = null;
        RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                requestFuture,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onTaskComplete(error);
                    }
                });

        requestQueue.add(request);
        try {
            response = requestFuture.get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        } catch (TimeoutException e) {
        }
        return response;
    }
}

