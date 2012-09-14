package my.andr.task.flag1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PageTwo extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.two);
		
		Button l=(Button)findViewById(R.id.launch);
		l.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				TaskViewer.show(PageTwo.this);
			}
		});	
		Button b3=(Button)findViewById(R.id.three);
		b3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i=new Intent("Three");
				startActivity(i);
			}
		});
	}
}