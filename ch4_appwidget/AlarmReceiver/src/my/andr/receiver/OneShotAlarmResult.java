package my.andr.receiver;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

public class OneShotAlarmResult extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Result:Add Ordered result", Toast.LENGTH_SHORT).show();
		Log.i("log", "Result:Add Ordered result");
	}
}
