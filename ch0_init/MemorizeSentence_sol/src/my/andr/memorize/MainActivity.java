package my.andr.memorize;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        addEvents();
    }//end onCreate
    private void addEvents() {
		findViewById(R.id.btn_exercise).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),SentenceListActivity.class);
				startActivity(intent);
			}
		});
	}//end addEvents
}//end MainActivity