package com.lge.screen;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

public class MyProvider extends ContentProvider {
	public static final Uri CONTENT_URI
	 =Uri.parse("content://com.lge.screen.MyProvider/trains");
	
	@Override
	public boolean onCreate() {
		Toast.makeText(getContext(), "onCreate()", Toast.LENGTH_SHORT).show();
		return false;
	}
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Toast.makeText(getContext(), "query()", Toast.LENGTH_SHORT).show();
		return null;
	}
	@Override
	public String getType(Uri uri) {
		Toast.makeText(getContext(), "getType()", Toast.LENGTH_SHORT).show();
		return null;
	}
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Toast.makeText(getContext(), "insert()", Toast.LENGTH_SHORT).show();
		return null;
	}
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		Toast.makeText(getContext(), "delete()", Toast.LENGTH_SHORT).show();
		return 0;
	}
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		Toast.makeText(getContext(), "update()", Toast.LENGTH_SHORT).show();
		return 0;
	}
}
