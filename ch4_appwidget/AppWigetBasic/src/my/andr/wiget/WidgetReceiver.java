package my.andr.wiget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class WidgetReceiver extends AppWidgetProvider {
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.smile);
		views.setTextViewText(R.id.smileText, "" + "Smile" + "%");
		AppWidgetManager wm = AppWidgetManager.getInstance(context);

		wm.updateAppWidget(appWidgetIds, views);
		Toast.makeText(context, "onUpdate() length="+appWidgetIds.length+" Ids[0]="+appWidgetIds[0],Toast.LENGTH_SHORT).show();
		Log.i("log", "onUpdate() length="+appWidgetIds.length+" Ids[0]="+appWidgetIds[0]);
	}

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		Toast.makeText(context, "onEnabled()", Toast.LENGTH_SHORT).show();
		Log.i("log", "onEnabled()");
	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
		Toast.makeText(context, "onDisabled()", Toast.LENGTH_SHORT).show();
		Log.i("log", "onDisabled()");
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
		Toast.makeText(context, "onDeleted()", Toast.LENGTH_SHORT).show();
		Log.i("log", "onDeleted()");
	}
}
