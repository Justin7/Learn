package my.andr.callback.service;

import my.andr.callback.client.ICallback;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.widget.Toast;

public class AIDLService extends Service {
	private int value = 0;
	private final RemoteCallbackList<ICallback> mCallbacks = new RemoteCallbackList<ICallback>();

	private final IService.Stub mBinder = new IService.Stub() {
		@Override
		public void registerCallback(ICallback cb) throws RemoteException {
			if (cb != null) mCallbacks.register(cb);
		}
		@Override
		public void unregisterCallback(ICallback cb) throws RemoteException {
			if (cb != null) mCallbacks.unregister(cb);
		}		
		@Override
		public void setValue(int v) throws RemoteException {
			value = v * v;
			final int callbacks = mCallbacks.beginBroadcast();
			for (int i = 0; i < callbacks; i++) {
				try {
					mCallbacks.getBroadcastItem(i).valueChanged(value);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			mCallbacks.finishBroadcast();
		}
		@Override
		public int getValue() throws RemoteException {
			return value;
		}
		public void sendMessage(String msg) {
			final int callbacks = mCallbacks.beginBroadcast();
			for (int i = 0; i < callbacks; i++) {
				try {
					mCallbacks.getBroadcastItem(i).receiveMessage("Service:" + msg);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			mCallbacks.finishBroadcast();
		}
	};
	
	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
	}
	@Override
	public IBinder onBind(Intent intent) {
		Toast.makeText(this, "onBind()", Toast.LENGTH_SHORT).show();
		return mBinder;
	}
	@Override
	public boolean onUnbind(Intent intent) {
		Toast.makeText(this, "onUnbind()", Toast.LENGTH_SHORT).show();
		return super.onUnbind(intent);
	}
	@Override
	public void onDestroy() {
		Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}
}