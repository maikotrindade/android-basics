package br.trindade.androidbasics.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
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
public class SavingFilesActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnInternalPrivate;
    private Button mBtnExternal;
    private Button mBtnExternalRemovable;

    public static final String TAG = SavingFilesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_files);
        bindButtons();
    }

    public void bindButtons() {
        mBtnInternalPrivate = AppUtil.get(findViewById(R.id.internalPrivate));
        mBtnInternalPrivate.setOnClickListener(this);
        mBtnExternal = AppUtil.get(findViewById(R.id.external));
        mBtnExternal.setOnClickListener(this);
        mBtnExternalRemovable = AppUtil.get(findViewById(R.id.externalRemovable));
        mBtnExternalRemovable.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.internalPrivate:
                internalPrivate();
                break;
            case R.id.external:
                external();
                break;
            case R.id.externalRemovable:
                externalRemovable();
                break;
        }
    }

    /**
     * This area of storage is sort of private to the application.
     * It is always available to the application and gets purged
     * when the app is uninstalled by the user.
     */
    private void internalPrivate() {
        String fileName = "MyFile.txt";
        String content = "internalPrivate";
        FileOutputStream outputStream;
        try {
            //The constant Context.MODE_PRIVATE makes the file inaccessible to other apps
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
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
            String filename = "MyExternalFile.txt";
            File file = new File(myDir, filename);
            if (file.exists()) file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);
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
            myDir.mkdirs();
            String filename = "MyExternalFile.txt";
            File file = new File(myDir, filename);
            if (file.exists()) file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);
                out.flush();
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
}
