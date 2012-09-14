package my.andr.receiver;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

public class OneShotAlarm3 extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Alarm3:PRIORITY 1000", Toast.LENGTH_SHORT).show();
		Log.i("log", "Alarm3:PRIORITY 1000");
	}
}
