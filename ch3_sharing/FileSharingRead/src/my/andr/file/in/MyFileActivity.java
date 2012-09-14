package my.andr.file.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MyFileActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Toast.makeText(this, "Read-UID:" + Process.myPid(), Toast.LENGTH_SHORT).show();
		Log.i("log", "IN  myUid="+Process.myUid()+" myPid="+Process.myPid()+" myTid="+Process.myTid());
		
		TextView t = (TextView) findViewById(R.id.text1);
		BufferedReader br = null;

		try {
			Context c = createPackageContext("my.andr.file.out", Context.CONTEXT_IGNORE_SECURITY);
			br = new BufferedReader(new InputStreamReader(c.openFileInput("data.txt")));
			String msg = br.readLine();
			while (msg != null) {
				t.append(msg + "\n");
				msg = br.readLine();
			}
		} catch (Exception e) {
			Log.e("IO", "File Input Error" + e);
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}