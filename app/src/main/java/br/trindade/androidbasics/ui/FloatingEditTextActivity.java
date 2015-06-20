package br.trindade.androidbasics.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.trindade.androidbasics.util.AppUtil;

public class FloatingEditTextActivity extends ActionBarActivity implements View.OnTouchListener {

    private EditText editTextLocation;
    private EditText editTextUpload;
    private EditText editTextDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_edit_text);
        bindButtons();
    }

    public void bindButtons() {
        editTextLocation = AppUtil.get(findViewById(R.id.editTextLocation));
        editTextLocation.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_action_room, 0);
        editTextLocation.setOnTouchListener(this);

        editTextUpload = AppUtil.get(findViewById(R.id.editTextUpload));
        editTextUpload.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_file_cloud_upload, 0);
        editTextUpload.setOnTouchListener(this);

        editTextDate = AppUtil.get(findViewById(R.id.editTextDate));
        editTextDate.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_editor_insert_invitation, 0);
        editTextDate.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        final int DRAWABLE_RIGHT = 2;
        if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (view.getId()) {
                case R.id.editTextLocation:
                    showMessage(editTextLocation, DRAWABLE_RIGHT, event);
                    break;

                case R.id.editTextUpload:
                    showMessage(editTextUpload, DRAWABLE_RIGHT, event);
                    break;

                case R.id.editTextDate:
                    showMessage(editTextDate, DRAWABLE_RIGHT, event);
                    break;
            }
        }
        return false;
    }

    private void showMessage(EditText editText, int drawableRight, MotionEvent event) {
        if (event.getRawX() >= (editText.getRight() -
                editText.getCompoundDrawables()[drawableRight].getBounds().width())) {
            Toast.makeText(FloatingEditTextActivity.this, "Event based on touch on drawable bounds", Toast.LENGTH_SHORT).show();
        }
    }
}
