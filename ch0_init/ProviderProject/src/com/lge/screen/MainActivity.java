package com.lge.screen;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button insert=(Button)findViewById(R.id.insertBt);
        insert.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				getContentResolver()
				.insert(MyProvider.CONTENT_URI, 
						null/*values*/);
			}
		});
        Button delete=(Button)findViewById(R.id.deleteBt);
        delete.setOnClickListener(new OnClickListener(){
        	public void onClick(View v) {
        		getContentResolver()
        		.delete(MyProvider.CONTENT_URI, 
        				null/*where*/, null/*selectionArgs*/);
        	}
        });
        Button update=(Button)findViewById(R.id.updateBt);
        update.setOnClickListener(new OnClickListener(){
        	public void onClick(View v) {
        		getContentResolver()
        		.update(MyProvider.CONTENT_URI, 
        				null/*values*/, 
        				null/*where*/, null/*selectionArgs*/);
        	}
        });
        Button query=(Button)findViewById(R.id.queryBt);
        query.setOnClickListener(new OnClickListener(){
        	public void onClick(View v) {
        		getContentResolver()
        		.query(MyProvider.CONTENT_URI, 
        			   null/*projection*/, 
        			   null/*selection*/, null/*selectionArgs*/,
        			   null/*sortOrder*/);
        	}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
