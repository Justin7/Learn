package my.andr.task.flag1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PageOne extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one);
		
		Button l=(Button)findViewById(R.id.launch);
		l.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				TaskViewer.show(PageOne.this);
			}
		});	
		Button b2=(Button)findViewById(R.id.two);
		b2.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent i=new Intent("Two");
				startActivity(i);
			}
		});
		Button b3=(Button)findViewById(R.id.three);
		b3.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent i=new Intent("Three");
				
				//1.호출된 객체와 같은 객체가 stack에 있다면 마지막 객체를 Top으로 옮긴다.
				i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); //1-2-3-4-1 >> 1-2-4-1-3 onNewIntent()
				
				//2.stack에 존재하는 객체를 다시 호출시 존재하는 객체까지 stack지우고 호출
				//i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //1-2-3-4-1 >> 1-2-new3
				
				//3.stack에 존재하는 객체를 다시 호출시 존재하는 객체전까지 stack지우고 SINGLE_TOP으로 지정됨
				//i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP); //1-2-3-4-1 >> 1-2-old3 onNewIntent()
				
				//4.Service 또는 BroadcastReceiver에서 Activity 호출시 사용해야 함
				//i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				
				//5.Third 페이지를 history에 남기지 않음
				//i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				
				startActivity(i);
			}
		});
	}
}