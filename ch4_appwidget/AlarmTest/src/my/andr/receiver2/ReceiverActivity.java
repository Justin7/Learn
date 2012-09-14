package my.andr.receiver2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ReceiverActivity extends Activity {
	public static final String RECEIVER_ACTION="my.andr.RECEIVER";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	
	public void onSendCall(View v){
		Intent i = new Intent(RECEIVER_ACTION);
		sendBroadcast(i);
	}
}
