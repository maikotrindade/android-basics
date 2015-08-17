package br.trindade.androidbasics.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.trindade.androidbasics.util.AndroidUtil;

/**
 * @author maiko.trindade
 */
public class FloatingEditTextActivity extends BaseActivity implements View.OnTouchListener {

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
        editTextLocation = AndroidUtil.get(findViewById(R.id.editTextLocation));
        editTextLocation.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_action_room, 0);
        editTextLocation.setOnTouchListener(this);

        editTextUpload = AndroidUtil.get(findViewById(R.id.editTextUpload));
        editTextUpload.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_file_cloud_upload, 0);
        editTextUpload.setOnTouchListener(this);

        editTextDate = AndroidUtil.get(findViewById(R.id.editTextDate));
        editTextDate.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_editor_insert_invitation, 0);
        editTextDate.setOnTouchListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_info) {
            AndroidUtil.getSimpleDialog(FloatingEditTextActivity.this, getString(R.string.msg_floatingtext_info))
                    .show();
        }
        return super.onOptionsItemSelected(item);
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
