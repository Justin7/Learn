package my.andr.callback.client;

import my.andr.callback.service.IService;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AIDLClient extends Activity {
	private boolean serviceFlag = false;
	private IService mService;
	private EditText et;
	private TextView tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		et=(EditText)findViewById(R.id.input_value);
		tv=(TextView)findViewById(R.id.output_value);

		Button m_bindservice=(Button)findViewById(R.id.bind_service);
		m_bindservice.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Toast.makeText(AIDLClient.this, R.string.bindservice, Toast.LENGTH_SHORT).show();
				Intent intent=new Intent();
				intent.setClassName(
					"my.andr.callback.service",
					"my.andr.callback.service.AIDLService");
				serviceFlag=bindService(intent, conn, Context.BIND_AUTO_CREATE);
			}
		});
		Button m_unbindservice=(Button)findViewById(R.id.unbind_service);
		m_unbindservice.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Toast.makeText(AIDLClient.this, R.string.unbindservice, Toast.LENGTH_SHORT).show();
				if(serviceFlag) {
					unbindService(conn);
					serviceFlag = false;
				}				
			}
		});
		Button m_getResult=(Button)findViewById(R.id.get_result);
		m_getResult.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				if (serviceFlag && mService != null){
					final String input=et.getText().toString();
					try{
						try{
							mService.setValue(Integer.parseInt(input));
						}catch(NumberFormatException e){
							mService.sendMessage(input);
						}
						et.setText("");
						final String result=new Integer(mService.getValue()).toString();
						tv.setText(result);
					}catch(RemoteException e1){
						e1.printStackTrace();
					}
				}
			}
		});
	}
	private ServiceConnection conn = new ServiceConnection(){
		@Override
		public void onServiceConnected(ComponentName name, IBinder service){
			Toast.makeText(AIDLClient.this, R.string.service_connected, Toast.LENGTH_SHORT).show();
			mService=IService.Stub.asInterface(service);
			try{
				mService.registerCallback(callback);
			}catch(RemoteException e){
				e.printStackTrace();
			}
		}
		@Override
		public void onServiceDisconnected(ComponentName name){
			Toast.makeText(AIDLClient.this, R.string.service_disconntected, Toast.LENGTH_SHORT).show();
		}
	};

	private static final int VALUE_MSG  = 1;
	private static final int STRING_MSG = 2;
	
	private ICallback callback = new ICallback.Stub(){
		@Override
		public void valueChanged(int value) throws RemoteException{
			mHandler.sendMessage(mHandler.obtainMessage(VALUE_MSG, value, 0));
		}
		public void receiveMessage(String msg){
			mHandler.sendMessage(mHandler.obtainMessage(STRING_MSG, msg));
		}
	};
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			TextView output=(TextView)findViewById(R.id.callback_value);
			switch (msg.what){
				case VALUE_MSG:
					output.setText("Received from service: " + msg.arg1);
					break;
				case STRING_MSG:
					output.setText("Received from service: " + msg.obj);
					break;
				default:
					super.handleMessage(msg);
			}
		}
	};
}