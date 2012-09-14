package my.andr.notepadv4.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.net.Uri;
import android.text.TextUtils;

public class NotesDbAdapter extends ContentProvider {
    private DatabaseHelper mOpenHelper;
    private static final String DATABASE_CREATE =
	    "create table notes (" +
	    "  _id integer primary key autoincrement, " +
	    "  title text not null, " + 
	    "  body text not null" +
	    ");";
    
    private static final String DATABASE_NAME  = "data";
    private static final String DATABASE_TABLE = "notes";
    private static final int    DATABASE_VERSION = 2;

    private static final int NOTES   = 1;
    private static final int NOTE_ID = 2;
    private static final UriMatcher sUriMatcher;  
    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(Notes.AUTHORITY, "notes",   NOTES);
        sUriMatcher.addURI(Notes.AUTHORITY, "notes/#", NOTE_ID);
    }
    
	private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);
        }
    }
	
    @Override
	public boolean onCreate() {
		mOpenHelper=new DatabaseHelper(getContext());
		return  true;
	}    
    public void close() {
    	mOpenHelper.close();
    }
    
    private int checkUri(Uri uri){
        int i = sUriMatcher.match(uri);
        if(i == -1) {
        	throw new IllegalArgumentException("Unknown URI " + uri);
        }
        return i;
    }
    private String addWhereId(Uri uri, String where){
    	String noteId = uri.getPathSegments().get(1);
        return Notes._ID + "=" + noteId + 
               (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : "");
    }
    
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int match=checkUri(uri);
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		long rowId=-1;
		switch (match) {
			case NOTES:
				rowId = db.insert(DATABASE_TABLE, 
						          null, values);
				break;
		    case NOTE_ID:
		        throw new SQLException("Failed to insert row into " + uri);
		}
		if (rowId < 0) throw new SQLException("Failed to insert row into " + uri);
	    uri = ContentUris.withAppendedId(Notes.CONTENT_URI, rowId);
	    getContext().getContentResolver().notifyChange(uri, null);
	    return uri;		
	}	
    @Override
	public int delete(Uri uri, String where, String[] whereArgs) {
    	int match=checkUri(uri);
    	SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count=0;
        switch (match) {
	        case NOTES:
	            count = db.delete(DATABASE_TABLE, 
	            		          where, whereArgs);
	            break;
	        case NOTE_ID:
	            count = db.delete(DATABASE_TABLE, 
	            		          addWhereId(uri, where), whereArgs);
        }
        if (count < 1) throw new SQLException("Failed to delete row into " + uri);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
	}    
	@Override
	public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
		int match=checkUri(uri);
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count=0;
        switch (match) {
	        case NOTES:
	            count = db.update(DATABASE_TABLE, 
	            		          values, where, whereArgs);
	            break;
	        case NOTE_ID:
	            count = db.update(DATABASE_TABLE, 
	            		          values, addWhereId(uri, where), whereArgs);
        }
        if (count < 1) throw new SQLException("Failed to update row into " + uri);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
	}    
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		String orderBy;
		if (TextUtils.isEmpty(sortOrder)) {
		    orderBy = Notes.DEFAULT_SORT_ORDER;
		} else {
		    orderBy = sortOrder;
		}
		int match=checkUri(uri);
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();		
		Cursor c=null;
		switch (match) {
		case NOTES:
			c = db.query(DATABASE_TABLE, projection, 
					     selection, selectionArgs, 
					     null, null, orderBy);
		    break;
		case NOTE_ID:
			c = db.query(DATABASE_TABLE,  projection, 
					     addWhereId(uri, selection), selectionArgs, 
					     null, null, orderBy);
		}
		if (c == null) throw new SQLException("Failed to select row into " + uri);
		// Tell the cursor what uri to watch, so it knows when its source data changes
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;	   
	}	
	@Override
	public String getType(Uri uri) {
		Log.i("log","getType("+uri+")");
		switch (sUriMatcher.match(uri)) {
        case NOTES:
            return "vnd.android.cursor.dir/"+Notes.AUTHORITY;
        case NOTE_ID:
            return "vnd.android.cursor.item/"+Notes.AUTHORITY;
        default:
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
	}
}
