package my.andr.biner;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ServiceClient extends Activity {
	public ServiceConnection conn=null;
    private int statusValue = 0;

	   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        
	        conn=new ServiceConnection() {
	        	public void onServiceConnected(ComponentName name, IBinder service){
	        		IMyService myService = (IMyService) service;
	        		statusValue = myService.getStatus();
	        		Toast.makeText(ServiceClient.this, 
	        			"onServiceConnected()..."+statusValue, Toast.LENGTH_LONG).show();
	        	}
	        	public void onServiceDisconnected(ComponentName name){
	        		Toast.makeText(ServiceClient.this, 
	        			"onServiceDisconnected()", Toast.LENGTH_LONG).show();
	        	}
	        };
        
	        Button bindButton = (Button) findViewById(R.id.bindButton);
	        bindButton.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	    			Intent i = new Intent();
	    			ComponentName comp = new ComponentName(
						"my.andr.biner", 
	    				"my.andr.biner.MyService"); 
	    			i.setComponent(comp); 
	   				bindService(i,conn, Context.BIND_AUTO_CREATE);                     
	   			}
	        });
	        Button unbindButton = (Button)findViewById(R.id.unbindButton);
	        unbindButton.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	   				unbindService(conn);                     
	   			}
	        });
	    }
}