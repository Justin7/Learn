package my.andr.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button b=(Button)findViewById(R.id.next);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i=new Intent();
				i.setClass(MainActivity.this, NextActivity.class);
				startActivity(i);
			}
		});
	}
}