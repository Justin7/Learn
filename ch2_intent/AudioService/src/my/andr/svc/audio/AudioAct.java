package my.andr.svc.audio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AudioAct extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button play=(Button)findViewById(R.id.playButton);
		play.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(AudioAct.this, AudioService.class);
				startService(i);
			}
		});
		Button stop=(Button)findViewById(R.id.stopButton);		
		stop.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(AudioAct.this, AudioService.class);
				stopService(i);
			}
		});
	}

}