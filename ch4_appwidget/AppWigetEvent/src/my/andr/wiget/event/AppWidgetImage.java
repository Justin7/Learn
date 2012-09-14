package my.andr.wiget.event;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;

public class AppWidgetImage extends Service implements Runnable {
	private Context context;
	private int count;
	private int mId;

	private Handler h = new Handler();
	
	private SharedPreferences pref;
	private SharedPreferences.Editor edit;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		context = this;
		count = 0;
		mId = intent.getIntExtra("wId", 10);
		pref = getSharedPreferences("WidgetReceiver", Context.MODE_WORLD_WRITEABLE);
		edit = pref.edit();

		h.postDelayed(this, 1000);

		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	public void run() {
		if (count > 5) return;
		if (pref.getBoolean("widgetImage", true)) {
			WidgetReceiver.appWidgetUpdate(context, mId, R.drawable.angry);
			edit.putBoolean("widgetImage", false);
			edit.commit();
		} else {
			WidgetReceiver.appWidgetUpdate(context, mId, R.drawable.smile);
			edit.putBoolean("widgetImage", true);
			edit.commit();
		}
		count++;
		h.postDelayed(this, 2000);
	}
}
