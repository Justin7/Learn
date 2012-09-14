package my.andr.my.home;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;

//어플리케이션 정보
public class AppInfo {
	public CharSequence title; // 타이틀
	public Drawable icon; // 아이콘
	public Intent intent; // 인텐트

	// 액티비티의 지정 （7）
	public void setActivity(String packageName, String className) {
		intent = new Intent(Intent.ACTION_MAIN);
		intent.___________(Intent.CATEGORY_LAUNCHER);
		intent.____________(new ComponentName(packageName, className));
		intent.________(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
	}
}