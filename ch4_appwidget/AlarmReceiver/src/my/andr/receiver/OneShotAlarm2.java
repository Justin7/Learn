package my.andr.receiver;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

public class OneShotAlarm2 extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Alarm2:PRIORITY 500", Toast.LENGTH_SHORT).show();
		Log.i("log", "Alarm2:PRIORITY 500");
	}
}
