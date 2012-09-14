package my.andr.wiget.event;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetReceiver extends AppWidgetProvider {
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		for (int i = 0; i < appWidgetIds.length; i++) {
			appWidgetUpdate(context, appWidgetIds[i], R.drawable.smile);
			//Toast.makeText(context, "onUpdate() length="+appWidgetIds.length+" Ids["+i+"]="+appWidgetIds[i],Toast.LENGTH_SHORT).show();
			//Log.i("log", "onUpdate() length="+appWidgetIds.length+" Ids["+i+"]="+appWidgetIds[i]);
		}
	}
	static void appWidgetUpdate(Context context, int appWidgetId, int imgId) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.smile);
		AppWidgetManager wm = AppWidgetManager.getInstance(context);

		views.setImageViewResource(R.id.smileImage, imgId);

		Intent intentImage = new Intent(context, AppWidgetImage.class);
		intentImage.putExtra("wId", appWidgetId);
		PendingIntent pendingImage = PendingIntent.getService(context, appWidgetId, intentImage, PendingIntent.FLAG_UPDATE_CURRENT); 
		views.setOnClickPendingIntent(R.id.smileImage, pendingImage);

		Intent intentText = new Intent(context, AppWidgetText.class);
		intentText.putExtra("wId",  appWidgetId);
		intentText.putExtra("wImg", imgId);
		PendingIntent pendingText = PendingIntent.getActivity(context, appWidgetId, intentText, PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.smileText, pendingText);

		wm.updateAppWidget(appWidgetId, views);
	}
	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		//Toast.makeText(context, "onEnabled()", Toast.LENGTH_SHORT).show();
		//Log.i("log", "onEnabled()");
	}
	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
		//Toast.makeText(context, "onDisabled()", Toast.LENGTH_SHORT).show();
		//Log.i("log", "onDisabled()");
	}
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
		//Toast.makeText(context, "onDeleted()", Toast.LENGTH_SHORT).show();
		//Log.i("log", "onDeleted()");
	}
}
