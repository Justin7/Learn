package my.andr.dice;

import java.util.Random;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

// 홈 페이지 위젯을 제어하는 서비스
public class WidgetService extends Service {
	private static final String ACTION_BTNCLICK = "my.andr.ACTION_BTNCLICK";
	
	int[] ids = { 
		R.drawable.dice1, 
		R.drawable.dice2, 
		R.drawable.dice3,
		R.drawable.dice4, 
		R.drawable.dice5, 
		R.drawable.dice6 
	};
	
	// 서비스 시작 시에 호출된다.
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		
		// 원격 뷰의 생성 (3)
		RemoteViews view = new RemoteViews(getPackageName(), R.layout.appwidget);

		// PendingIntent의 설정 (4)
		Intent in = new Intent();
		in.setAction(ACTION_BTNCLICK);
		PendingIntent pending = PendingIntent.__________(this, 0, in, PendingIntent.FLAG_UPDATE_CURRENT);
		view.setOnClickPendingIntent(R.id._________, pending);

		Log.i("log", "intent.getAction(): "+intent.getAction());
		// 이미지 뷰가 클릭될 때의 처리 (5)
		if (ACTION_BTNCLICK.equals(intent.getAction())) {
			int idx = new Random().nextInt(6);
			view.____________________(R.id.imageview, ids[idx]);
		}

		// 홈 스크린 위젯의 화면 갱신 (6)
		AppWidgetManager manager = AppWidgetManager.getInstance(this);
		ComponentName widget = new ComponentName(
			"my.andr.dice",
			"my.andr.dice.AppWidgetDice");
		manager._______________(widget, view);
		
		return START_STICKY;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
