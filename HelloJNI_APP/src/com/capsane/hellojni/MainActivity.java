package com.capsane.hellojni;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.
    static {
        try {
            System.loadLibrary("simp");
        	Log.e("loadLibrary", "libsimp is loaded");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("EEEEE", "libsimp not loaded!!!!!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        String takePicture1 = "" + getFromJNI("TAKE_PICTURE");
        setFromJNI("TAKE_PICTURE", 1);
        String takePicture2 = "" + getFromJNI("TAKE_PICTURE");
        tv.setText(takePicture1 + " set to " + takePicture2);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native int setFromJNI(String resourceType, int accessFlag);
    public native int getFromJNI(String resourceType);
}

