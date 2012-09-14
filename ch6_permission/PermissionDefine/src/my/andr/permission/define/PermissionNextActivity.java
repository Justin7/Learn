package my.andr.permission.define;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PermissionNextActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView tv = new TextView(this);
		tv.setText("Permission이 있으면 호출됩니다.");
		setContentView(tv);
	}
}
