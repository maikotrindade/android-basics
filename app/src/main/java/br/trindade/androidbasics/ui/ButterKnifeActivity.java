package br.trindade.androidbasics.ui;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.trindade.androidbasics.ui.R;
import br.trindade.androidbasics.util.AndroidUtil;
import butterknife.Bind;
import butterknife.BindDrawable;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButterKnifeActivity extends BaseActivity {

    @Bind(R.id.my_button)
    Button myButton;

    @Bind(R.id.my_button2)
    Button myButton2;

    @Bind(R.id.my_imageview)
    ImageView myImageView;

    @BindDrawable(R.mipmap.ic_launcher)
    Drawable myDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);
        ButterKnife.bind(this);
        useMyViews();

        //Initial test, note the `onClick` listener is actuate here!
        showToast(myButton);
    }

    private void useMyViews() {
        myButton.setText("Don't you dare to click me!");
        myButton2.setText("Just click!");
        myImageView.setImageDrawable(myDrawable);
    }

    @OnClick({R.id.my_button, R.id.my_button2})
    public void showToast(View view) {
        Toast.makeText(getBaseContext(), "This is just a Toast", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_info) {
            AndroidUtil.getSimpleDialog(ButterKnifeActivity.this, getString(R.string.msg_butterknife_info))
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }
}
