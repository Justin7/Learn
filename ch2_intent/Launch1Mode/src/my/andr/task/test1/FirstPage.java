package my.andr.task.test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstPage extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);
		
		Button l=(Button)findViewById(R.id.launch);
		l.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				TaskViewer.show(FirstPage.this);
			}
		});		
		Button b2=(Button)findViewById(R.id.second);
		b2.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent i=new Intent("Second");
				startActivity(i);
			}
		});
	}
}