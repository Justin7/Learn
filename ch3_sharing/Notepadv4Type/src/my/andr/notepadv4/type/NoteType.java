package my.andr.notepadv4.type;

import my.andr.notepadv4.provider.Notes;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NoteType extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button list=(Button)findViewById(R.id.list);
		list.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_VIEW, Notes.CONTENT_URI);
				startActivity(i);
			}
		});
		Button view=(Button)findViewById(R.id.view);
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri uri = ContentUris.withAppendedId(Notes.CONTENT_URI, 2);
				Intent i = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(i);
			}
		});
	}
}