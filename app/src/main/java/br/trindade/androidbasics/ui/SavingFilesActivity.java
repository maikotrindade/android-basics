package br.trindade.androidbasics.ui;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import br.trindade.androidbasics.util.AppUtil;

/**
 * @author maiko.trindade
 */
public class SavingFilesActivity extends BaseActivity implements View.OnClickListener {

    public static final String FILENAME = "androidBasicFile.txt";
    public static final String FILE_CONTENT = "Hello, this is just a example of saving Files in Android";

    private Button mBtnInternalPrivate;
    private Button mBtnExternal;
    private Button mBtnExternalRemovable;
    private Button mMoveFromCache;

    public static final String TAG = SavingFilesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_files);
        bindButtons();
    }

    public void bindButtons() {
        mBtnInternalPrivate = AppUtil.get(findViewById(R.id.internal_private));
        mBtnInternalPrivate.setOnClickListener(this);
        mBtnExternal = AppUtil.get(findViewById(R.id.external));
        mBtnExternal.setOnClickListener(this);
        mBtnExternalRemovable = AppUtil.get(findViewById(R.id.external_removable));
        mBtnExternalRemovable.setOnClickListener(this);
        mMoveFromCache = AppUtil.get(findViewById(R.id.move_cache_other_folder));
        mMoveFromCache.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.internal_private:
                internalPrivate();
                break;
            case R.id.external:
                external();
                break;
            case R.id.external_removable:
                externalRemovable();
                break;
            case R.id.move_cache_other_folder:
                renameMoveCached();
                break;
        }
    }

    /**
     * This area of storage is sort of private to the application.
     * It is always available to the application and gets purged
     * when the app is uninstalled by the user.
     */
    private void internalPrivate() {
        FileOutputStream outputStream;
        try {
            //The constant Context.MODE_PRIVATE makes the file inaccessible to other apps
            outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            outputStream.write(FILE_CONTENT.getBytes());
            outputStream.close();
            Toast.makeText(SavingFilesActivity.this, "File saved: Internal And Private", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * It can be either removable like an SD card or non-removable in which case it is internal.
     *
     * Using getExternalStorageState() we can get the current state of the primary external storage device.
     * If it’s equal to Environment.MEDIA_MOUNTED then we’ll have read/write access.
     * The directory can be external removable or external inside of internal (emulated) in case of absense
     * of SD memory(external storage)
     *
     * Starting with Android 4.4 Kitkat (API 19), reading and writing private files in the private directories
     * of the external store do not require permissions in AndroidManifest.xml.
     */
    private void external() {

        if (isExternalStorageWritable()) {
            String folder = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(folder + "/savingExternalTest");
            myDir.mkdirs();
            File file = new File(myDir, FILENAME);
            if (file.exists()) file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);
                out.write(FILE_CONTENT.getBytes());
                out.flush();
                out.close();
                Toast.makeText(SavingFilesActivity.this, "File saved: External", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    /**
     * Save files only if there is an external removable storage available
     */
    private void externalRemovable() {
        if (isExternalStorageRemovableWritable()) {
            String folder = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(folder + "/savingExternalTest");
            if (!myDir.exists()) {
                myDir.mkdirs();
            }
            File file = new File(myDir, FILENAME);
            if (file.exists()) file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);
                out.flush();
                out.write(FILE_CONTENT.getBytes());
                out.close();
                Toast.makeText(SavingFilesActivity.this, "File saved: External and Removable (SD)", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    /* Checks if external storage is available for read and write */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private boolean isExternalStorageRemovableWritable() {
        if (Environment.isExternalStorageRemovable() && isExternalStorageWritable()) {
            return true;
        }
        return false;
    }

    public void renameMoveCached() {
        if (isExternalStorageWritable()) {
            File fileCached = new File(getCacheDir(), FILENAME);

            try {
                FileOutputStream out = new FileOutputStream(fileCached);
                out.write(FILE_CONTENT.getBytes());
                out.flush();
                out.close();
                Toast.makeText(SavingFilesActivity.this, "File saved: Cache", Toast.LENGTH_SHORT).show();

                ContextWrapper cw = new ContextWrapper(getBaseContext());
                File directory = cw.getDir("myFolder", Context.MODE_PRIVATE);

                boolean success = fileCached.renameTo(new File(directory, FILENAME + "_renamed"));
                if (success) {
                    Toast.makeText(SavingFilesActivity.this, "File renamed and located in: Cache in "
                            + directory.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SavingFilesActivity.this, "Nice try, It didn't work ;]", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }
}
