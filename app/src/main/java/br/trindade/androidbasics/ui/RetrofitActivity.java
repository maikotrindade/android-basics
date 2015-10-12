package br.trindade.androidbasics.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.trindade.androidbasics.api.GitApi;
import br.trindade.androidbasics.model.GitUser;
import br.trindade.androidbasics.util.AndroidUtil;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RetrofitActivity extends BaseActivity {

    static private final String API = "https://api.github.com";

    private EditText mEditTextSearch;
    private TextView mUserName, mUserLocation, mUserBlog;
    private LinearLayout mContentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.title_activity_retrofit));
        setContentView(R.layout.activity_retrofit);
        bindElements();
    }

    private void bindElements() {
        mContentLayout = AndroidUtil.get(findViewById(R.id.response_content));
        mUserName = AndroidUtil.get(findViewById(R.id.txt_name));
        mUserLocation = AndroidUtil.get(findViewById(R.id.txt_location));
        mUserBlog = AndroidUtil.get(findViewById(R.id.txt_blog));
        mEditTextSearch = AndroidUtil.get(findViewById(R.id.editTextQuery));

        mEditTextSearch = AndroidUtil.get(findViewById(R.id.editTextQuery));
        mEditTextSearch.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_action_search, 0);
        mEditTextSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, MotionEvent motionEvent) {
                String user = mEditTextSearch.getText().toString();
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint(API).build();
                GitApi git = restAdapter.create(GitApi.class);

                git.getFeed(user, new Callback<GitUser>() {
                    @Override
                    public void success(GitUser gitUser, Response response) {
                        mContentLayout.setVisibility(View.VISIBLE);
                        mUserName.setText(gitUser.getName());
                        mUserLocation.setText(gitUser.getLocation());
                        mUserBlog.setText(gitUser.getBlog());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        mContentLayout.setVisibility(View.INVISIBLE);
                        Snackbar.make(view, "Error: " + error.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                });
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_info) {
            AndroidUtil.getSimpleDialog(RetrofitActivity.this, getString(R.string.msg_retrofit_info)).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
