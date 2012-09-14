package my.andr.biner;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service{

	private MyServiceBinder myServiceBinder = new MyServiceBinder();
	
	public class MyServiceBinder extends Binder implements IMyService {
		private int status=10;
		public int getStatus() {
			return status;
		}
	}
	@Override
	public IBinder onBind(Intent intent) {
		Toast.makeText(this, "onBind()", Toast.LENGTH_LONG).show();
		return myServiceBinder;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "onCreate()", Toast.LENGTH_LONG).show();
	}
	@Override
	public boolean onUnbind(Intent intent) {
		Toast.makeText(this, "onUnbind()", Toast.LENGTH_LONG).show();
		return super.onUnbind(intent);
	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		Toast.makeText(this, "onDestroy()", Toast.LENGTH_LONG).show();
	}
}