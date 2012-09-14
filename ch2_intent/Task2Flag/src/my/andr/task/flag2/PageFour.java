package my.andr.task.flag2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PageFour extends Activity{
	@Override
	protected void onNewIntent(Intent intent){
		super.onNewIntent(intent);
		Log.i("log","onNewIntent() 4");
		Log.i("log",".");
	}
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.four);
		
		Button l=(Button)findViewById(R.id.launch);
		l.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				TaskViewer.show(PageFour.this);
			}
		});	
		Button b1=(Button)findViewById(R.id.one);
		b1.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent i=new Intent("One");
				startActivity(i);
			}
		});
		Button b4=(Button)findViewById(R.id.my);
		b4.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent i=new Intent("Four");
				startActivity(i);
			}
		});
	}
}