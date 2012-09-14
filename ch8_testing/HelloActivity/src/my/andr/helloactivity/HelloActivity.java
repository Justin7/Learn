package my.andr.helloactivity;

import android.app.Activity;
import android.os.Bundle;

public class HelloActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for this activity.  You can find it
        // in res/layout/hello_activity.xml
        setContentView(R.layout.hello_activity);
    }
}

