package my.andr.receiver;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

public class OneShotAlarm4 extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Alarm4:After Sticky......", Toast.LENGTH_SHORT).show();
		Log.i("log", "Alarm4:After Sticky......");
	}
}
