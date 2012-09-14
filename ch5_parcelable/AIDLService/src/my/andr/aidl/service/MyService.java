package my.andr.aidl.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;
import android.os.Process;

public class MyService extends Service {
	private IBinder ib = new IMyService.Stub() {
		public int getStatus() {
			return Process.myPid();
		}
	};
	@Override
	public IBinder onBind(Intent intent) {
		Toast.makeText(this, "onBind()", Toast.LENGTH_SHORT).show();
		return ib;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
	}
	@Override
	public boolean onUnbind(Intent intent) {
		Toast.makeText(this, "onUnbind()", Toast.LENGTH_SHORT).show();
		return super.onUnbind(intent);
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
	}
}