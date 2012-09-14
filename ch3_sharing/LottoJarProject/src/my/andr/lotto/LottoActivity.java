package my.andr.lotto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import my.andr.share.lotto.Lotto;

public class LottoActivity extends Activity {
	Lotto lo;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lo = new Lotto();
		
		setContentView(R.layout.main);
		Button mButton = (Button) findViewById(R.id.playButton);
		mButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(LottoActivity.this, "행운의 숫자: " + lo.getValues(),
						Toast.LENGTH_LONG).show();
			}
		});
	}

}