package my.andr.file.out;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MyFileOutActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Toast.makeText(this, "Save-UID:"+Process.myPid(), Toast.LENGTH_SHORT).show();
		Log.i("log", "OUT myUid="+Process.myUid()+" myPid="+Process.myPid()+" myTid="+Process.myTid());

		TextView t = (TextView) findViewById(R.id.text1);
		BufferedWriter bw = null;
		try {                          // MODE_WORLD_WRITEABLE | MODE_WORLD_READABLE | MODE_PRIVATE 
			bw = new BufferedWriter(new OutputStreamWriter(openFileOutput("data.txt", MODE_WORLD_READABLE)));
			bw.append("안녕하세요.\n");
			bw.append("반갑습니다.");
			
			t.setText("파일이 정상적으로 생성되었습니다.");
		} catch (IOException e) {
			Log.e("IO", "File Output Error");
			t.setText("파일이 생성시 오류가 발생했습니다.");
		} finally {
			try {
				if (bw != null) bw.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

	}
}