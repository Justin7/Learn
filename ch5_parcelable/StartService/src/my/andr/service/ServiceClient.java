package my.andr.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiceClient extends Activity {
	Context c=this; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
				Intent i=new Intent();
    		    ComponentName comp=new ComponentName(
    		    	"my.andr.service", 
    		    	"my.andr.service.MyService"); 
    		    i.setComponent(comp);
    			startService(i);                    
   			}
        });    
        Button stopButton = (Button) findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
				Intent i=new Intent(c,MyService.class);
   		        stopService(i);   
   			}
        }); 
    }
}