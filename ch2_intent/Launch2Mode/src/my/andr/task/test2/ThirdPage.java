package my.andr.task.test2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ThirdPage extends Activity{
	@Override
	protected void onNewIntent(Intent intent){
		super.onNewIntent(intent);
		Log.i("log","onNewIntent() 3");
		Log.i("log",".");
	}
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third);

		Button l=(Button)findViewById(R.id.launch);
		l.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				TaskViewer.show(ThirdPage.this);
			}
		});		
		Button b4=(Button)findViewById(R.id.fourth);
		b4.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent i=new Intent("Fourth");
				startActivity(i);
			}
		});
		Button b3=(Button)findViewById(R.id.my);
		b3.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent i=new Intent("Third");
				startActivity(i);
			}
		});
	}
}