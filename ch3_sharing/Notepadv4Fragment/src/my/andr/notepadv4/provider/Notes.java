package my.andr.notepadv4.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public  final class Notes implements BaseColumns {
	public static final String AUTHORITY="my.andr.notepadv4.provider";
	public static final Uri    CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/notes");

	public static final String DEFAULT_SORT_ORDER="title";

	public static final String TITLE="title";
	public static final String BODY ="body";
}  
