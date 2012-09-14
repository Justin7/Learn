package my.andr.receiver;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

public class OneShotAlarmReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "AlarmR:Add IntentFilter......", Toast.LENGTH_SHORT).show();
		Log.i("log", "AlarmR:Add IntentFilter......");
	}
}
