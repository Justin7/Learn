package my.andr.receiver;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

public class OneShotAlarmFilter extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Filter:Add IntentFilter", Toast.LENGTH_SHORT).show();
		Log.i("log", "Filter:Add IntentFilter");
	}
}
