package br.trindade.androidbasics.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import br.trindade.androidbasics.adapter.AdapterMovies;
import br.trindade.androidbasics.callback.BoxOfficeMoviesLoadedListener;
import br.trindade.androidbasics.database.DBMovies;
import br.trindade.androidbasics.model.Movie;
import br.trindade.androidbasics.task.TaskLoadMoviesBoxOffice;
import br.trindade.androidbasics.ui.R;
import br.trindade.androidbasics.util.BasicsApplication;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BoxOfficeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoxOfficeFragment extends Fragment implements BoxOfficeMoviesLoadedListener {

    private static final String STATE_MOVIES = "state_movies";
    private ArrayList<Movie> mListMovies = new ArrayList<>();
    private AdapterMovies mAdapter;
    private RecyclerView mRecyclerMovies;
    private TextView mTextError;

    public BoxOfficeFragment() {}

    public static BoxOfficeFragment newInstance() {
        BoxOfficeFragment fragment = new BoxOfficeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_box_office, container, false);
        bindElements(layout);
        loadData(savedInstanceState);
        return layout;
    }

    private void bindElements(final View layout) {
        mTextError = (TextView) layout.findViewById(R.id.textVolleyError);

        mRecyclerMovies = (RecyclerView) layout.findViewById(R.id.list_movies);
        mRecyclerMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new AdapterMovies(getActivity());
        mRecyclerMovies.setAdapter(mAdapter);
    }

    private void loadData(Bundle state) {
        if (state != null) {
            mListMovies = state.getParcelableArrayList(STATE_MOVIES);
        } else {
            mListMovies = BasicsApplication.getWritableDatabase().readMovies(DBMovies.BOX_OFFICE);
            if (mListMovies.isEmpty()) {
                new TaskLoadMoviesBoxOffice(this).execute();
            }
        }
        mAdapter.setMovies(mListMovies);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save the movie list to a parcelable prior to rotation or configuration change
        outState.putParcelableArrayList(STATE_MOVIES, mListMovies);
    }


    private void handleVolleyError(VolleyError error) {
        mTextError.setVisibility(View.VISIBLE);
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            mTextError.setText(R.string.error_timeout);
        } else if (error instanceof AuthFailureError) {
            mTextError.setText(R.string.error_auth_failure);
        } else if (error instanceof ServerError) {
            mTextError.setText(R.string.error_auth_failure);
        } else if (error instanceof NetworkError) {
            mTextError.setText(R.string.error_network);
        } else if (error instanceof ParseError) {
            mTextError.setText(R.string.error_parser);
        }
    }

    /**
     * Called when the AsyncTask finishes load the list of movies from the web
     */
    @Override
    public void onBoxOfficeMoviesLoaded(ArrayList<Movie> listMovies) {
        mAdapter.setMovies(listMovies);
    }
}

