package com.lge.screen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context c, Intent i) {
		Log.i("log", "onReceive()");
		Toast.makeText(c, "onReceive()", Toast.LENGTH_SHORT).show();
	}
}
