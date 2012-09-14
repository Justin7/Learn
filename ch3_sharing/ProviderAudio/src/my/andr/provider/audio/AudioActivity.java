package my.andr.provider.audio;

import android.app.ListActivity;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.ToggleButton;

public class AudioActivity extends ListActivity {
	private ToggleButton tb;
	private Uri uri;
	
	private void fillData(){
		boolean instorage=tb.isChecked();
		uri=instorage ? Audio.Media.EXTERNAL_CONTENT_URI :
			            Audio.Media.INTERNAL_CONTENT_URI;
		
		CursorLoader cl = new CursorLoader(this, uri,
				null, null,
				null, null);
		Cursor c = cl.loadInBackground();
		
		if(c!=null) detailAudio(c);
		
		String[] from = new String[] { MediaColumns.TITLE, MediaColumns.DISPLAY_NAME };
		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, 
			android.R.layout.simple_list_item_2, c, from, to, 0);

		setListAdapter(adapter);
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		tb=(ToggleButton)findViewById(R.id.storage);
		tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				fillData();
			}
		});
		
		fillData();
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Uri u=ContentUris.withAppendedId(uri, id);
		Intent i=new Intent(Intent.ACTION_VIEW, u);
		startActivity(i);
	}
	private void detailAudio(Cursor c) {
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













