package my.andr.task.test2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FourthPage extends Activity{
	@Override
	protected void onNewIntent(Intent intent){
		super.onNewIntent(intent);
		Log.i("log","onNewIntent() 4");
		Log.i("log",".");
	}	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fourth);
		
		Button l=(Button)findViewById(R.id.launch);
		l.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				TaskViewer.show(FourthPage.this);
			}
		});		
		Button b1=(Button)findViewById(R.id.first);
		b1.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent i=new Intent("First");
				startActivity(i);
			}
		});
		Button b4=(Button)findViewById(R.id.my);
		b4.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent i=new Intent("Fourth");
				startActivity(i);
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