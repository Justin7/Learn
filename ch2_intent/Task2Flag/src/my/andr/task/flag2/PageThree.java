package my.andr.task.flag2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PageThree extends Activity{
	@Override
	protected void onNewIntent(Intent intent){
		super.onNewIntent(intent);
		Log.i("log","onNewIntent() 3");
		Log.i("log",".");
	}
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.three);

		Button l=(Button)findViewById(R.id.launch);
		l.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				TaskViewer.show(PageThree.this);
			}
		});	
		Button b4=(Button)findViewById(R.id.four);
		b4.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent i=new Intent("Four");
				startActivity(i);
			}
		});
		Button b3=(Button)findViewById(R.id.my);
		b3.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent i=new Intent("Three");
				startActivity(i);
			}
		});
		Button b33=(Button)findViewById(R.id.myTop);
		b33.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent i=new Intent("Three");
				i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(i);
			}
		});		
	}
}