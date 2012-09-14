package my.andr.provider.settings;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BrightnessActivity extends Activity{
	TextView tv;
	EditText et;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tv=(TextView)findViewById(R.id.tv);
		et=(EditText)findViewById(R.id.edit);
		// 화면 밝기 조정창 호출
		Button b=(Button)findViewById(R.id.next);
		b.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent i = new Intent(android.provider.Settings.ACTION_DISPLAY_SETTINGS);
				startActivity(i);
			}
		});
		// Settings 내부 값 변경
		Button e=(Button)findViewById(R.id.exec);
		e.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				int newValue=Integer.parseInt(et.getText().toString());
				
				// 1. 값 변경하기
				//android.provider.Settings.System.putInt(
				//	getContentResolver(),     
				//	android.provider.Settings.System.SCREEN_BRIGHTNESS,     
				//	newValue
				//);
				
				// 2. 값 변경하기
				String name=android.provider.Settings.System.SCREEN_BRIGHTNESS;
				ContentValues cv = new ContentValues();
		        cv.put("value", newValue);
		        getContentResolver().update(
		        		android.provider.Settings.System.CONTENT_URI,
		        		cv,"name='"+name+"'",null);	
		        
		        refresh();
			}
		});
		
	}
	@Override
	protected void onResume() {
		refresh();
		super.onResume();
	}
	private void refresh(){
		// 1. 값조회하기
		//String brightness=android.provider.Settings.System.getString(
		//	getContentResolver(),
		//	android.provider.Settings.System.SCREEN_BRIGHTNESS
		//);
		
		// 2. 값조회하기
		String name=android.provider.Settings.System.SCREEN_BRIGHTNESS;
        Cursor c=getContentResolver().query(android.provider.Settings.System.CONTENT_URI,
        		new String[]{"_id", "name", "value"},
        		"name='"+name+"'",null,
        		null);	
        c.moveToFirst();
        String brightness=c.getString(c.getColumnIndexOrThrow("value"));
        
		Log.i("log", "brightness=" + brightness);
		
		tv.setText("화면(0~255)밝기: " + brightness);
		et.setText(brightness);
	}
}