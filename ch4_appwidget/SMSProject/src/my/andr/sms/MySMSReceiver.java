package my.andr.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MySMSReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context c, Intent i) {
		Bundle bundle = i.getExtras();
		Object messages[] = (Object[]) bundle.get("pdus");
		SmsMessage smsMessage[] = new SmsMessage[messages.length];
		int n = 0;
		for (Object msg : messages) {
			smsMessage[n++] = SmsMessage.createFromPdu((byte[]) msg);
		}
		Toast.makeText(c,
			"MySMSReceiver : " + smsMessage[0].getMessageBody(),
			Toast.LENGTH_LONG).show();
	}

}
