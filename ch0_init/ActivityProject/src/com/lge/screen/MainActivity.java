package com.lge.screen;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button b=(Button)findViewById(R.id.nextBt);
        b.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent i=new Intent();
				i.setClass(
					MainActivity.this, 
					NextActivity.class
				);
				startActivity(i);
			}
		});
        Log.i("log", "onCreate()");
        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
    }
    @Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		//Log.i("log", "onRestoreInstanceState()");
        //Toast.makeText(this, "onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
	}
	@Override
	protected void onStart() {
		super.onStart();
		Log.i("log", "onStart()");
        Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("log", "onRestart()");
        Toast.makeText(this, "onRestart()", Toast.LENGTH_SHORT).show();
	}
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("log", "onResume()");
        Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show();
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		//Log.i("log", "onSaveInstanceState()");
        //Toast.makeText(this, "onSaveInstanceState", Toast.LENGTH_SHORT).show();
	}
	@Override
	protected void onPause() {
		super.onPause();
		Log.i("log", "onPause()");
        Toast.makeText(this, "onPause()", Toast.LENGTH_SHORT).show();
	}
	@Override
	protected void onStop() {
		super.onStop();
		Log.i("log", "onStop()");
        Toast.makeText(this, "onStop()", Toast.LENGTH_SHORT).show();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("log", "onDestroy()");
        Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
		//Log.i("log", "onCreateOptionsMenu()");
        //Toast.makeText(this, "onCreateOptionsMenu()", Toast.LENGTH_SHORT).show();
        return true;
    }
}




















