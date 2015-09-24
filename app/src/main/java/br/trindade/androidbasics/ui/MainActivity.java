package br.trindade.androidbasics.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.trindade.androidbasics.util.AndroidUtil;

/**
 * @author maiko.trindade
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnLifecycle, mBtnSwipeTabs, mBtnFloatEdtTxt, mBtnSavingFiles, mBtnVolley,
    mBtnCollapseToolbar, mBtnParseXML;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindButtons();
    }

    public void bindButtons() {
        mBtnLifecycle = AndroidUtil.get(findViewById(R.id.btn_lifecycle));
        mBtnLifecycle.setOnClickListener(this);
        mBtnSwipeTabs = AndroidUtil.get(findViewById(R.id.btn_swipe_tabs));
        mBtnSwipeTabs.setOnClickListener(this);
        mBtnFloatEdtTxt = AndroidUtil.get(findViewById(R.id.btn_floating_edit_text));
        mBtnFloatEdtTxt.setOnClickListener(this);
        mBtnSavingFiles = AndroidUtil.get(findViewById(R.id.btn_saving_files));
        mBtnSavingFiles.setOnClickListener(this);
        mBtnVolley = AndroidUtil.get(findViewById(R.id.btn_volley));
        mBtnVolley.setOnClickListener(this);
        mBtnCollapseToolbar = AndroidUtil.get(findViewById(R.id.btn_collapse_toolbar));
        mBtnCollapseToolbar.setOnClickListener(this);
        mBtnParseXML = AndroidUtil.get(findViewById(R.id.btn_parse_xml));
        mBtnParseXML.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_lifecycle:
                startActivity(new Intent(MainActivity.this, LifecycleActivity.class)); break;
            case R.id.btn_swipe_tabs:
                startActivity(new Intent(MainActivity.this, SwipeTabsActivity.class)); break;
            case R.id.btn_floating_edit_text:
                startActivity(new Intent(MainActivity.this, FloatingEditTextActivity.class)); break;
            case R.id.btn_saving_files:
                startActivity(new Intent(MainActivity.this, SavingFilesActivity.class)); break;
            case R.id.btn_volley:
                startActivity(new Intent(MainActivity.this, VolleyActivity.class)); break;
            case R.id.btn_collapse_toolbar:
                startActivity(new Intent(MainActivity.this, CollapseToolbarActivity.class)); break;
            case R.id.btn_parse_xml:
                startActivity(new Intent(MainActivity.this, ParseXMLActivity.class)); break;
        }
    }
}
