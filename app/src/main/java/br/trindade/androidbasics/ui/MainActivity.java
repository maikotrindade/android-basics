package br.trindade.androidbasics.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.trindade.androidbasics.ui.swipeTabs.SwipeTabsMainActivity;
import br.trindade.androidbasics.util.AppUtil;

/**
 * @author maiko.trindade
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnLifecycle;
    private Button mBtnSwipeTabs;
    private Button mBtnFloatEdtTxt;
    private Button mBtnSavingFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindButtons();
    }

    public void bindButtons() {
        mBtnLifecycle = AppUtil.get(findViewById(R.id.lifecycle));
        mBtnLifecycle.setOnClickListener(this);
        mBtnSwipeTabs = AppUtil.get(findViewById(R.id.swipeTabs));
        mBtnSwipeTabs.setOnClickListener(this);
        mBtnFloatEdtTxt = AppUtil.get(findViewById(R.id.floatingEditText));
        mBtnFloatEdtTxt.setOnClickListener(this);
        mBtnSavingFiles = AppUtil.get(findViewById(R.id.savingFiles));
        mBtnSavingFiles.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lifecycle:
                startActivity(new Intent(MainActivity.this, LifecycleActivity.class)); break;
            case R.id.swipeTabs:
                startActivity(new Intent(MainActivity.this, SwipeTabsMainActivity.class)); break;
            case R.id.floatingEditText:
                startActivity(new Intent(MainActivity.this, FloatingEditTextActivity.class)); break;
            case R.id.savingFiles:
                startActivity(new Intent(MainActivity.this, SavingFilesActivity.class)); break;
        }
    }
}
