package my.andr.my.home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

//홈 스크린
public class MyHome extends Activity implements AdapterView.OnItemClickListener {
	private ArrayList<AppInfo> appList;// 어플리케이션 리스트
	private final BroadcastReceiver appReceiver = new AppReceiver();// 어플리케이션 등록
																	// 해제 리시버
	private GridView gridView; // 그리드 뷰

	// 생성
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// 레이아웃의 생성
		LinearLayout layout = new LinearLayout(this);
		layout.setBackgroundColor(Color.BLACK);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);

		// 그리드 뷰의 생성 (1）
		gridView = new GridView(this);
		gridView.setNumColumns(4);
		gridView.setGravity(Gravity.CENTER);
		gridView.setOnItemClickListener(this);
		gridView.setLayoutParams(
			new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT
			)
		);
		layout.addView(gridView);

		// 어플리케이션 리스트 읽기
		loadAppList();

		// 어플리케이션 등록 해제 리시버의 시작 (5）
		IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
		filter._________(Intent.ACTION_PACKAGE_REMOVED);
		filter._________(Intent.ACTION_PACKAGE_CHANGED);
		filter._____________("package");
		// 리시버의 등록
		________________(appReceiver, filter);
	}

	// 어플리케이션 리스트 읽기
	private void loadAppList() {
		// 액티비티 정보의 리스트 구하기 （3）
		PackageManager manager = _________________();
		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		intent.___________(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> apps = manager.queryIntentActivities(intent, 0);
		Collections.sort(apps, new ResolveInfo.DisplayNameComparator(manager));

		// 액티비티 정보 구하기 （4）
		appList = new ArrayList<AppInfo>();
		if (apps == null) return;
		for (int i = 0; i < apps.size(); i++) {
			AppInfo appInfo = new AppInfo();
			ResolveInfo info = apps.get(i);
			appInfo.title = info.loadLabel(manager);
			appInfo.setActivity(
				info.activityInfo.applicationInfo.packageName, 
				info.activityInfo.name
			);
			appInfo.icon = resizeIcon(info.activityInfo.loadIcon(manager));
			appList.add(appInfo);
		}

		// 그리드 갱신 （2）
		gridView.setAdapter(new GridAdapter(this));
	}

	// 아이콘 크기 변경
	private Drawable resizeIcon(Drawable icon) {
		// 준비 아이콘 크기 구하기
		Resources res = getResources();
		int width = (int) res.getDimension(android.R.dimen.app_icon_size);
		int height = (int) res.getDimension(android.R.dimen.app_icon_size);

		// 현재 아이콘 크기 구하기
		int iconWidth = icon.getIntrinsicWidth();
		int iconHeight = icon.getIntrinsicHeight();

		// 아이콘 크기의 변경
		if (width > 0 && height > 0
				&& (width < iconWidth || height < iconHeight)) {

			// 변환 후의 아이콘 크기 계산
			float ratio = (float) iconWidth / iconHeight;
			if (iconWidth > iconHeight) {
				height = (int) (width / ratio);
			} else if (iconHeight > iconWidth) {
				width = (int) (height * ratio);
			}

			// 동적 캔버스 생성
			Bitmap.Config c = (icon.getOpacity() != PixelFormat.OPAQUE) ? 
				Bitmap.Config.ARGB_8888	: Bitmap.Config.RGB_565;
			Bitmap thumb = Bitmap.createBitmap(width, height, c);
			Canvas canvas = new Canvas(thumb);
			canvas.setDrawFilter(new PaintFlagsDrawFilter(Paint.DITHER_FLAG, 0));

			// 동적 캔버스에 아이콘 그리기
			Rect oldBounds = new Rect();
			oldBounds.set(icon.getBounds());
			icon.setBounds(0, 0, width, height);
			icon.draw(canvas);
			icon.setBounds(oldBounds);

			// 캔버스를 Drawable 객체로 변환
			icon = new BitmapDrawable(getResources(), thumb);
		}
		return icon;
	}

	// 그리드 항목의 클릭 이벤트 처리
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		AppInfo appInfo = (AppInfo) parent.getItemAtPosition(position);
		// 액티비티의 기동		
		_____________(appInfo.intent);
	}

	// 어플리케이션 해제
	@Override
	public void onDestroy() {
		super.onDestroy();
		// 리시버의 해제
		__________________(appReceiver);
	}

	// BACK 키의 무효화 （6）
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (event.getKeyCode()) {
				case KeyEvent.KEYCODE_BACK:
					return true;
			}
		}
		return super.dispatchKeyEvent(event);
	}

	// 그리드 어댑터의 생성 （2）
	public class GridAdapter extends BaseAdapter {
		private Context context;// 컨텍스트

		// 생성자
		public GridAdapter(Context c) {
			context = c;
		}

		// 항목 수 구하기
		public int getCount() {
			return appList.size();
		}

		// 항목 구하기
		public Object getItem(int position) {
			return appList.get(position);
		}

		// 항목 ID 구하기
		public long getItemId(int position) {
			return position;
		}

		// 뷰 구하기
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textView = new TextView(context);
			textView.setWidth(78);
			textView.setHeight(80);
			// textView.setSingleLine(true);
			textView.setTextSize(12.0f);
			textView.setGravity(Gravity.CENTER_HORIZONTAL);
			textView.setTextColor(Color.WHITE);
			textView.setCompoundDrawablesWithIntrinsicBounds(
				null, appList.get(position).icon, null, null);
			textView.setText(appList.get(position).title);
			return textView;
		}
	}

	// 어플리케이션 등록 해제 리시버 （5）
	private class AppReceiver extends BroadcastReceiver {
		@Override
		public void _________(Context context, Intent intent) {
			// 어플리케이션 리스트 읽기
			loadAppList();
		}
	}
}


