package my.andr.provider.calllog;

import android.app.ListActivity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

public class CallLogActivity extends ListActivity {
	private Cursor c;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		CursorLoader cl = new CursorLoader(this, CallLog.Calls.CONTENT_URI,
				null, null,
				null, null);
		c = cl.loadInBackground(); 

		if(c!=null) detailCallLog(c);
		
		String[] from = new String[] { CallLog.Calls.NUMBER, CallLog.Calls.TYPE };
		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, 
			android.R.layout.simple_list_item_2, c, from, to, 0);

		adapter.setViewBinder(new ViewBinder() {
			@Override
			public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
				switch (view.getId()) {
					case android.R.id.text2:
						TextView tv = (TextView) view;
						int value = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
						switch (value) {
							case CallLog.Calls.INCOMING_TYPE:
								tv.setText("수신");
								break;
							case CallLog.Calls.MISSED_TYPE:
								tv.setText("부재중 통화");
								break;
							case CallLog.Calls.OUTGOING_TYPE:
								tv.setText("발신");
								break;
						}
						return true;
				}
				return false;
			}
		});
		setListAdapter(adapter);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		//1.
		c.moveToPosition(position);
		String s=c.getString(c.getColumnIndexOrThrow(CallLog.Calls.NUMBER));		
		Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel://"+s));
		//2.
		//Uri uri=ContentUris.withAppendedId(CallLog.Calls.CONTENT_URI, id);
		//Intent i=new Intent(Intent.ACTION_VIEW, uri);
				
		startActivity(i);
	}
	private void detailCallLog(Cursor c) {
		int col=c.getColumnCount();
		Log.i("log","c.getColumnCount()="+col);

		for(int i=0; i<col; i++){
			Log.i("log",i+" "+c.getColumnName(i));
		}
		
		int row=c.getCount();
		Log.i("log",".");
		Log.i("log","c.getCount()="+row);
		StringBuilder sb=new StringBuilder();
		while(c.moveToNext()){
			for(int i=0; i<col; i++){
				sb.append(c.getString(i)+" \t");
			}
			Log.i("log",sb.toString());
			sb.setLength(0);
		}
	}
}













