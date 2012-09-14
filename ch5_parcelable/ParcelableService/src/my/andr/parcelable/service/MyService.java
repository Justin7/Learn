package my.andr.parcelable.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.widget.Toast;

public class MyService extends Service{
    private IBinder ib = new IMyService.Stub() {
        public int getStatus(){
               return Process.myPid();
        }
        public SendObject  getObject(){
        	return new SendObject(10,"hello");
        }
    };
	public IBinder onBind(Intent intent) {
		Toast.makeText(this, "onBind()", Toast.LENGTH_SHORT).show();
		return ib;
	}	
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
	}
	public boolean onUnbind(Intent intent) {
		Toast.makeText(this, "onUnbind()", Toast.LENGTH_SHORT).show();
		return super.onUnbind(intent);
	}
	public void onDestroy(){
		super.onDestroy();
		Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
	}
}