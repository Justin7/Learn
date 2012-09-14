package com.lge.screen;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button start=(Button)findViewById(R.id.startBt);
        start.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent i=new Intent();
				i.setClass(MainActivity.this, MyService.class);
				startService(i);
			}
		});
        Button stop=(Button)findViewById(R.id.stopBt);
        stop.setOnClickListener(new OnClickListener(){
        	public void onClick(View v) {
        		Intent i=new Intent();
        		i.setClass(MainActivity.this, MyService.class);
        		stopService(i);
        	}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
