package my.andr.searchable.dict2;

import android.app.Activity;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class WordActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word);

        Uri uri = getIntent().getData();
        CursorLoader cl = new CursorLoader(this,  
				uri,
    			null, 
    			null, null, 
    			null);
        Cursor cursor=cl.loadInBackground();
    	
        if (cursor == null) {
            finish();
        } else {
            cursor.moveToFirst();

            TextView word = (TextView) findViewById(R.id.word);
            TextView definition = (TextView) findViewById(R.id.definition);

            int wIndex = cursor.getColumnIndexOrThrow(DictionaryDatabase.KEY_WORD);
            int dIndex = cursor.getColumnIndexOrThrow(DictionaryDatabase.KEY_DEFINITION);

            word.setText(cursor.getString(wIndex));
            definition.setText(cursor.getString(dIndex));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                onSearchRequested();
                return true;
            default:
                return false;
        }
    }
}
