package my.andr.task.flag2;

import java.util.List;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

public class TaskViewer {
	public static void show(Context c){
		ActivityManager m = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningTaskInfo> tList = m.getRunningTasks(10);
		for (ActivityManager.RunningTaskInfo info : tList) {
			int cnt = info.numActivities;
			int id = info.id;
			int n = info.numRunning;
			String t = info.topActivity.getClassName();
			String cName = info.baseActivity.getClassName();
			Log.i("log", String.format("11ACT ID[%3d] ROOT[%30s] num[%2d] run[%2d] TOP[%30s]",id,cName,cnt,n,t));
		}
		Log.i("log",".");
	}
}
