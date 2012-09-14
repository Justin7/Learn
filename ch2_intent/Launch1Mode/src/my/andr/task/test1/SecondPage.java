package my.andr.task.test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondPage extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
		Button l=(Button)findViewById(R.id.launch);
		l.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v){
				TaskViewer.show(SecondPage.this);
			}
		});		
		Button b3=(Button)findViewById(R.id.third);
		b3.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent i=new Intent("Third");
				startActivity(i);
			}
		});
	}
}