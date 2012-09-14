package my.andr.fragment.life;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Toast.makeText(this, "Activity.onCreate() start", Toast.LENGTH_SHORT).show();
		Log.i("log","Activity.onCreate() start");        
        setContentView(R.layout.main);
		Toast.makeText(this, "Activity.onCreate() end", Toast.LENGTH_SHORT).show();
		Log.i("log","Activity.onCreate() end"); 
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
		Toast.makeText(this, "Activity.onSaveInstanceState()", Toast.LENGTH_SHORT).show();
		Log.i("log","Activity.onSaveInstanceState()");    	
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	super.onRestoreInstanceState(savedInstanceState);
		Toast.makeText(this, "Activity.onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
		Log.i("log","Activity.onRestoreInstanceState()");     	
    }
    @Override
	protected void onStart() {
		super.onStart();
		Toast.makeText(this, "Activity.onStart()", Toast.LENGTH_SHORT).show();
		Log.i("log","Activity.onStart()");
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		Toast.makeText(this, "Activity.onRestart()", Toast.LENGTH_SHORT).show();
		Log.i("log",".");
		Log.i("log","Activity.onRestart()");
	}
	@Override
	protected void onResume() {
		super.onResume();
		Toast.makeText(this, "Activity.onResume()", Toast.LENGTH_SHORT).show();
		Log.i("log","Activity.onResume()");
	}
	@Override
	protected void onPause() {
		super.onPause();
		Toast.makeText(this, "Activity.onPause()", Toast.LENGTH_SHORT).show();
		Log.i("log","Activity.onPause()");
	}
	@Override
	protected void onStop() {
		super.onStop();
		Toast.makeText(this, "Activity.onStop()", Toast.LENGTH_SHORT).show();
		Log.i("log","Activity.onStop()");
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "Activity.onDestroy()", Toast.LENGTH_SHORT).show();
		Log.i("log","Activity.onDestroy()");
	}

	public static class MyFragment extends Fragment{
    	Activity act;
		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			act=activity;
			Toast.makeText(act, "Fragment.onAttach()", Toast.LENGTH_SHORT).show();
			Log.e("log","	Fragment.onAttach()");
		}
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			Toast.makeText(act, "Fragment.onCreate()", Toast.LENGTH_SHORT).show();
			Log.e("log","	Fragment.onCreate()");
		}
		@Override
		public void onSaveInstanceState(Bundle outState) {
			super.onSaveInstanceState(outState);
			Toast.makeText(act, "Fragment.onSaveInstanceState()", Toast.LENGTH_SHORT).show();
			Log.e("log","	Fragment.onSaveInstanceState()");			
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			Toast.makeText(act, "Fragment.onCreateView()", Toast.LENGTH_SHORT).show();
			Log.e("log","	Fragment.onCreateView()");
			TextView tv=new TextView(act);
			tv.setText(R.string.hello);
			tv.setGravity(Gravity.CENTER);
			return tv;
		}
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			Toast.makeText(act, "Fragment.onActivityCreated()", Toast.LENGTH_SHORT).show();
			Log.e("log","	Fragment.onActivityCreated()");
		}
		@Override
		public void onStart() {
			super.onStart();
			Toast.makeText(act, "Fragment.onStart()", Toast.LENGTH_SHORT).show();
			Log.e("log","	Fragment.onStart()");
		}
		@Override
		public void onResume() {
			super.onResume();
			Toast.makeText(act, "Fragment.onResume()", Toast.LENGTH_SHORT).show();
			Log.e("log","	Fragment.onResume()");
			Log.e("log","	.");
		}
		@Override
		public void onPause() {
			super.onPause();
			Toast.makeText(act, "Fragment.onPause()", Toast.LENGTH_SHORT).show();
			Log.e("log","	Fragment.onPause()");
		}
		@Override
		public void onStop() {
			super.onStop();
			Toast.makeText(act, "Fragment.onStop()", Toast.LENGTH_SHORT).show();
			Log.e("log","	Fragment.onStop()");
		}
		@Override
		public void onDestroyView() {
			super.onDestroyView();
			Toast.makeText(act, "Fragment.onDestroyView()", Toast.LENGTH_SHORT).show();
			Log.e("log","	Fragment.onDestroyView()");
		}
		@Override
		public void onDestroy() {
			super.onDestroy();
			Toast.makeText(act, "Fragment.onDestroy()", Toast.LENGTH_SHORT).show();
			Log.e("log","	Fragment.onDestroy()");
		}
		@Override
		public void onDetach() {
			super.onDetach();
			Toast.makeText(act, "Fragment.onDetach()", Toast.LENGTH_SHORT).show();
			Log.e("log","	Fragment.onDetach()");
		}
    }
}