package my.andr.start.command;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button startButton=(Button)findViewById(R.id.startButton);
		Button stopButton =(Button)findViewById(R.id.stopButton);

		startButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent();
				ComponentName comp = new ComponentName(
					"my.andr.start.command",
					"my.andr.start.command.MyService");
				i.setComponent(comp);
				i.putExtra("name", "ȫ�浿");
				startService(i);
			}
		});
		stopButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent();
				ComponentName comp = new ComponentName(
					"my.andr.start.command",
					"my.andr.start.command.MyService");
				i.setComponent(comp);
				stopService(i);
			}
		});
	}

}
