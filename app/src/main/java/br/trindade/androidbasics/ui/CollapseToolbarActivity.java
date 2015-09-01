package br.trindade.androidbasics.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.trindade.androidbasics.util.AndroidUtil;

public class CollapseToolbarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapse_toolbar);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String toolbarTitle = getResources().getString(R.string.title_activity_collapse_toolbar);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        collapsingToolbar.setTitle(toolbarTitle);
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.material_white_1000));
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.material_white_1000));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_info) {
            AndroidUtil.getSimpleDialog(CollapseToolbarActivity.this, getString(R.string.msg_collapsing_toolbar)).show();
        }
        return super.onOptionsItemSelected(item);
    }
}