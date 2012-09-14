package my.andr.aidl.client;

import my.andr.aidl.service.IMyService;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AIDLClient extends Activity {
	IMyService svc;
	ServiceConnection sc;
	boolean flag;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		sc=new ServiceConnection() {
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				svc=IMyService.Stub.asInterface(service);
				try {
					int pid=svc.getStatus();
					Toast.makeText(AIDLClient.this,"onServiceConnected()..."+pid, Toast.LENGTH_SHORT).show();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			@Override
			public void onServiceDisconnected(ComponentName name) {
				Toast.makeText(AIDLClient.this, "onServiceDisconnected()", Toast.LENGTH_SHORT).show();
			}
		};
		
		Button bind=(Button)findViewById(R.id.bindButton);
		bind.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent();
				ComponentName comp = new ComponentName(
					"my.andr.aidl.service",
					"my.andr.aidl.service.MyService");
				i.setComponent(comp);
				flag=bindService(i, sc, Context.BIND_AUTO_CREATE);
			}
		});
		Button unbind=(Button)findViewById(R.id.unbindButton);
		unbind.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(flag) {
					unbindService(sc);
					flag=false;
				}
			}
		});
	}
}