package my.andr.memorize.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class EngDbAdapter {

    private EngSentenceDBHelper mDbHelper;
    private SQLiteDatabase mDb;
    private final Context mCtx;
    private static EngDbAdapter instance;

	int[] startNo = new int[] { 1, 11, 21, 31, 41 };
	int[] endNo = new int[] { 10, 20, 30, 40, 50 };

   private EngDbAdapter(Context ctx) {
        this.mCtx = ctx;
        mDbHelper = new EngSentenceDBHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
    }
   public static EngDbAdapter getInstance(Context ctx){
	   if(instance== null){
		   instance=new EngDbAdapter(ctx);
	   }
	   return instance;
   }
    public void close() {
        mDbHelper.close();
    }
    public  Cursor  getQuery(int index){
		String[] sqlArgs = new String[] { startNo[index] + "",endNo[index] + "" };
		return mDb.query(DBConstants.TABLE_NAME,null," NO BETWEEN ? AND ? ORDER BY NO ASC ",sqlArgs,null,null,null);
    }
   public SentenceVO getSentence(String item_no) {
		SentenceVO result = null;
		//Cursor cursor = mDb.rawQuery(
		//		"SELECT no,eng_sentence,kor_sentence,labtime,last_accessed_time FROM "
		//				+ DBConstants.TABLE_NAME + " WHERE no = " + item_no, null);
		String[] fields=new String[]{"no","eng_sentence","kor_sentence","labtime","last_accessed_time"};
		Cursor cursor=mDb.query(DBConstants.TABLE_NAME, fields, " no = " + item_no, null, 
						null, null,null);
		if (cursor.moveToNext()) {
			result = new SentenceVO();
			result.setNo(cursor.getInt(0));
			result.setEngSentence(cursor.getString(1));
			result.setKorSentence(cursor.getString(2));
			result.setLabtime(cursor.getInt(3));
			result.setLastAccessedTime(cursor.getString(4));
		}
		cursor.close();
		return result;
	}
    public void updateLabtime(long labTime, String dateFormat , int no) {
		String sql = "UPDATE " + DBConstants.TABLE_NAME + " SET labtime = " + labTime
				+ ", last_accessed_time='" + dateFormat + "'"
				+ " WHERE no = " + no;
		mDb.execSQL(sql);
	}
}
