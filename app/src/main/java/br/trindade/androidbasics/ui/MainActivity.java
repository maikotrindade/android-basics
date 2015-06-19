package br.trindade.androidbasics.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.trindade.androidbasics.util.AppUtil;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLifecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindButtons();
    }

    public void bindButtons() {
       btnLifecycle = AppUtil.get(findViewById(R.id.lifecycle));
        btnLifecycle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.lifecycle :
                startActivity(new Intent(MainActivity.this, LifecycleActivity.class));
        }
    }
}