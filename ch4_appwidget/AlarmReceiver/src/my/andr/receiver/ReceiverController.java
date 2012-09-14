package my.andr.receiver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ReceiverController extends Activity {
	public static final String RECEIVER_ACTION="my.andr.RECEIVER";
	
	OneShotAlarm2 a2 = new OneShotAlarm2();
	OneShotAlarm3 a3 = new OneShotAlarm3();
	OneShotAlarm4 a4 = new OneShotAlarm4();

	Intent i = new Intent(RECEIVER_ACTION);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		IntentFilter fr = new IntentFilter(RECEIVER_ACTION);
		registerReceiver(new OneShotAlarmFilter(), fr);
	}

	public void onSendCall(View v){
		setReceiver();
		sendBroadcast(i); // 우선순위 없을 경우 : 랜덤
	}
	public void onOrderedCall(View v){
		//setReceiver();
		sendOrderedBroadcast(i, null);
	}
	public void onOrderedResult(View v){
		//setReceiver();
		sendOrderedBroadcast(i, null, new OneShotAlarmResult(), null, 0, null, null);
	}
	public void onStickyCall(View v){
		//setReceiver();
		sendStickyBroadcast(i);
	}
	public void onRegRecv(View v){
		IntentFilter f4 = new IntentFilter(RECEIVER_ACTION);
		registerReceiver(a4, f4);
	}
	public void onRemoveSticky(View v){
		removeStickyBroadcast(i);
	}
	public void onClear(View v){
		try {
			// unregisterReceiver(a2);
			unregisterReceiver(a3);
			unregisterReceiver(a4);
		} catch (Exception e) {
			Log.e("log", e.toString());
		}
	}
	
	public void setReceiver() {
		// SYSTEM_LOW_PRIORITY  = -1000
		// SYSTEM_HIGH_PRIORITY = 1000
		// default =0, LOW와 HIGH의 사이값으로 지정한다.
		Context c = getApplicationContext();
		IntentFilter f2 = new IntentFilter(RECEIVER_ACTION);
		f2.setPriority(500);
		c.registerReceiver(a2, f2);	
		
		IntentFilter f3 = new IntentFilter(RECEIVER_ACTION);
		f3.setPriority(1000);
		registerReceiver(a3, f3);	
	}
}
