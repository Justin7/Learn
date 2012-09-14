package my.andr.svc.audio;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class AudioService extends Service {
	private static MediaPlayer audio_play;
	private static final int notifyID = 1000;
	private NotificationManager nm;

	@Override
	public void onCreate() {
		nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		super.onCreate();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		play(R.raw.guitar_sim);
		showNotification();
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		stop();
		nm.cancel(notifyID);
	}
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	public void play(int resource) {
		stop();
		audio_play = MediaPlayer.create(this, resource);
		audio_play.setLooping(false);
		audio_play.start();
	}
	public void stop() {
		if (audio_play != null) {
			audio_play.stop();
			audio_play.release();
			audio_play = null;
		}
	}
	private void showNotification() {
		PendingIntent pi=PendingIntent.getActivity(this, 0,
			new Intent(this, AudioAct.class), 0);
		Notification n=new Notification.Builder(this)
			.setSmallIcon(R.drawable.stat_happy)
			.setContentTitle(getText(R.string.status_bar_info))
		    .setContentInfo(getText(R.string.status_bar_notifications_message))
		    .setContentText(getText(R.string.app_name))
		    .setTicker("Music Start.....")
		    .setContentIntent(pi)
			.build();
		nm.notify(notifyID, n);
	}
}
