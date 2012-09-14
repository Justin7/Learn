package my.andr.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
	}
	//@Override
	//public void onStart(Intent intent, int startId) {
	//	super.onStart(intent, startId);
	//	Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();			
	//}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "onStartCommand()", Toast.LENGTH_LONG).show();
		return START_STICKY;
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();			
	}
	@Override
	public IBinder onBind(Intent intent) {
		Toast.makeText(this, "onBind()", Toast.LENGTH_SHORT).show();			
		return null;
	}	
}