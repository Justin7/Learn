package my.andr.notepadv4.resolver;

import my.andr.notepadv4.provider.Notes;
import android.app.Activity;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteEdit extends Activity {
	private EditText mTitleText;
	private EditText mBodyText;
	private Long mRowId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_edit);

		mTitleText=(EditText)findViewById(R.id.title);
		mBodyText =(EditText)findViewById(R.id.body);
		Button confirm=(Button)findViewById(R.id.confirm);

		if(savedInstanceState != null){
			mRowId = savedInstanceState.getLong(Notes._ID);
		}
        if(mRowId == null) {
			Bundle extras=getIntent().getExtras();            
			if(extras != null){
				mRowId=extras.getLong(Notes._ID);
			}
		}
		confirm.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				setResult(RESULT_OK);
				finish();
			}
		});
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if(mRowId != null){
			outState.putLong(Notes._ID, mRowId);
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
		populateFields();
	}
	private void populateFields() {
		if(mRowId != null){
			CursorLoader cl = new CursorLoader(this,  
					Notes.CONTENT_URI,
					new String[] {Notes._ID, Notes.TITLE, Notes.BODY }, 
					Notes._ID+"="+mRowId, null, 
					null);

			Cursor c=cl.loadInBackground();
					
			c.moveToFirst();
			mTitleText.setText(c.getString(
				c.getColumnIndexOrThrow(Notes.TITLE)));
			mBodyText.setText(c.getString(
				c.getColumnIndexOrThrow(Notes.BODY)));
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}
	private void saveState() {
		ContentValues cv=new ContentValues();
		cv.put(Notes.TITLE, mTitleText.getText().toString());
		cv.put(Notes.BODY,  mBodyText.getText().toString());
		if(mRowId == null)
			getContentResolver()
			.insert(Notes.CONTENT_URI, 
					cv);
		else
			getContentResolver()
			.update(Notes.CONTENT_URI, 
					cv,
					Notes._ID+"="+mRowId, null);
	}
}
