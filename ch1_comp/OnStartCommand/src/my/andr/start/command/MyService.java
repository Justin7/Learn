package my.andr.start.command;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	public IBinder onBind(Intent intent) {
		Toast.makeText(this, "onBind()", Toast.LENGTH_LONG).show();
		Log.e("log", ".");
		Log.e("log", "onBind()");
		return null;
	}
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "onCreate()", Toast.LENGTH_LONG).show();
		Log.e("log", ".");
		Log.e("log", "onCreate() "+this.toString());
	}
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "onStartCommand(intent, "+flags+", "+startId+")", Toast.LENGTH_LONG).show();
		
		String in="";
		String name="";
		if(intent==null){
			in="null";
		}else{
			name=intent.getStringExtra("name");
			if(name==null){
				name="null";
			}
			in=intent.toString();
		}

		String fl="";
		switch(flags){
			case   0: fl="0"; 										break;
			case   1: fl="(1)START_FLAG_REDELIVERY"; 				break;
			case   2: fl="(2)START_FLAG_RETRY"; 					break;
			case   3: fl="(3)START_FLAG_PSJ?_RETRY_OR_REDELIVERY"; 	break; // PSJ?
			default : fl=""+flags;
		}
		
		int r=//START_STICKY;               //1
		      START_NOT_STICKY;           //2 
		      //START_REDELIVER_INTENT;     //3
		      //START_STICKY_COMPATIBILITY; //0
		String re="";
		switch(r){
			case 0: re="(0)START_STICKY_COMPATIBILITY"; break;
			case 1: re="(1)START_STICKY"; 				break;
			case 2: re="(2)START_NOT_STICKY"; 			break;
			case 3: re="(3)START_REDELIVER_INTENT"; 	break;
		}
		
		Log.e("log", ".");
		Log.e("log", "onStartCommand("+in+", "+fl+", "+startId+")");
		Log.e("log", "onStartCommand name="+name);
		Log.e("log", "onStartCommand return="+re);
		
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Log.e("log", "*");
		Log.e("log", "*onStartCommand("+in+", "+fl+", "+startId+")");
		Log.e("log", "*onStartCommand name="+name);
		Log.e("log", "*onStartCommand return="+re);
		
		return r;
	}	
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "onDestroy()", Toast.LENGTH_LONG).show();
		Log.e("log", ".");
		Log.e("log", "onDestroy()");
	}
}
