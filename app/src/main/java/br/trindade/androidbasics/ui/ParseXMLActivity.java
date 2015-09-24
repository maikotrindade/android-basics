package br.trindade.androidbasics.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.List;

import br.trindade.androidbasics.adapter.ArticleAdapter;
import br.trindade.androidbasics.callback.OnSuccessListener;
import br.trindade.androidbasics.model.Article;
import br.trindade.androidbasics.task.TaskTechCrunch;
import br.trindade.androidbasics.util.AndroidUtil;
import br.trindade.androidbasics.util.BasicsApplication;

public class ParseXMLActivity extends BaseActivity {

    public static final String TASK_FRAGMENT = ParseXMLActivity.class.getSimpleName();

    private PlaceHolderFragment taskFragment;
    private RecyclerView mArticleRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_xml);

        if (savedInstanceState == null) {
            taskFragment = new PlaceHolderFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(taskFragment, TASK_FRAGMENT).commit();
        } else {
            taskFragment = (PlaceHolderFragment) getSupportFragmentManager().findFragmentByTag(TASK_FRAGMENT);
        }

        taskFragment.startTask(new OnSuccessListener() {
            @Override
            public void onSuccess(List<Article> articles) {
                mArticleRecyclerView = (RecyclerView) findViewById(R.id.articles_recycler_view);
                mArticleRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(BasicsApplication.getAppContext());
                mArticleRecyclerView.setLayoutManager(mLayoutManager);
                mAdapter = new ArticleAdapter(articles);
                mArticleRecyclerView.setAdapter(mAdapter);
            }
        });
    }

    public static class PlaceHolderFragment extends Fragment {

        private TaskTechCrunch techCrunchTask;

        public PlaceHolderFragment() {
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            setRetainInstance(true);
        }

        public void startTask(final OnSuccessListener listener) {
            if (techCrunchTask != null) {
                techCrunchTask.cancel(true);
            } else {
                techCrunchTask = new TaskTechCrunch(listener);
                techCrunchTask.execute();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_info) {
            AndroidUtil.getSimpleDialog(ParseXMLActivity.this, getString(R.string.msg_parsing_xml))
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

}
