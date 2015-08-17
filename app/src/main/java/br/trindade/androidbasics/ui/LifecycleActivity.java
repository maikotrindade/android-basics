package br.trindade.androidbasics.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import br.trindade.androidbasics.util.AndroidUtil;

/**
 * @author maiko.trindade
 */
public class LifecycleActivity extends BaseActivity {

    public final static String TAG = LifecycleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        Log.i(TAG, "The onCreate() event");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_info) {
            AndroidUtil.getSimpleDialog(LifecycleActivity.this, getString(R.string.msg_lifecycle_info)).show();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when the activity is about to become visible.
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "The onStart() event");
    }

    /**
     * Called when the activity has become visible.
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "The onResume() event");
    }

    /**
     * Called when another activity is taking focus.
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "The onPause() event");
    }

    /**
     * Called when the activity is no longer visible.
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "The onStop() event");
    }

    /**
     * Called just before the activity is destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "The onDestroy() event");
    }
}
