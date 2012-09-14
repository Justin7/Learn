package my.andr.dice;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

// 홈 스크린 위젯 프로바이더 (1)
public class WidgetDice extends _________________ {
	// 홈 스크린 위젯 변경 시 호출된다 .
	@Override
	public void ________(Context c, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		Log.i("log", "onUpdate(): "+appWidgetIds[0]);
		// 홈 스크린 위젯 이벤트 처리를 담당하는 서비스 시작 (2)
		Intent intent = new Intent(c, WidgetService.class);
		c.startService(intent);
	}
}