package my.andr.wiget.event;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class AppWidgetText extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	public void onClick(View v){
		int mId  = getIntent().getIntExtra("wId",  0);
		int mImg = getIntent().getIntExtra("wImg", R.drawable.smile);

		if (mImg==R.drawable.smile) {
			WidgetReceiver.appWidgetUpdate(this, mId, R.drawable.angry);
		} else {
			WidgetReceiver.appWidgetUpdate(this, mId, R.drawable.smile);
		}
		finish();
	}
}
