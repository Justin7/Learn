package my.andr.memorize;

import java.text.SimpleDateFormat;
import java.util.Date;

import my.andr.memorize.db.EngDbAdapter;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class SentenceListActivity extends ____________ {
	private EngDbAdapter dao;
	int selectedCategoryIndex = 0;
	/**
	 * 객체 생성시 호출되는 메서드
	 * 객체 초기화 작업을 수행한다.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercise_alone);		
		dao = EngDbAdapter.___________(this);
		addEvents();
	}// end onCreate()
/**
 * 선택된 페이지 인덱스와 마지막 선택한 문장의 위치를 파악하여 표시한다.
 */
	@Override
	protected void onResume() {
		super.onResume();
		selectedCategoryIndex = ____________________("eng_sentence",MODE_WORLD_READABLE).getInt("selectedCategoryIndex",0);
		Spinner spinner = (Spinner) findViewById(R.id.alone_category);
		spinner.setSelection(selectedCategoryIndex);
		sentenceList();
		int lastPosition = ____________________("eng_sentence",MODE_WORLD_READABLE).getInt("last_position",0);
		getListView().setSelection(lastPosition);// 마지막 위치로 이동
	}
   /** 
    * ListView의 목록을 디스플레이한다.
    */
	@SuppressWarnings("deprecation")
	private void sentenceList() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		final int today = new Integer(sdf.format(new Date()));

		Cursor cursor = dao.________(selectedCategoryIndex);
		startManagingCursor(cursor);

		SimpleCursorAdapter exerciseSentenceListAdapter = new SimpleCursorAdapter(
				this, R.layout.exercise_alone_item,
				cursor, new String[] { "no", "kor_sentence","labtime", "last_accessed_time" },
				new int[] {
						R.id.exe_alone_list_no, R.id.exe_alone_list_sentence,
						R.id.exe_alone_list_status,
						R.id.exe_alone_list_lastaccesseedtime }, 0) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				Cursor cursor = getCursor();
				int columnIndex = cursor.getColumnIndex("last_accessed_time");
				int lat = cursor.getInt(columnIndex);
				int diff = lat - today;
				String msg = (lat == 0) ? "기록없음" : (diff < 0) ? diff * -1	+ "일전" : "오늘";
				TextView tv=(TextView) view.findViewById(R.id.exe_alone_list_sentence);
				if (diff == 0) {   //오늘 작업한 거라면
						tv.setBackgroundColor(Color.argb(100, 0, 200, 0));
						tv.setTextColor(Color.BLACK);
				} 
				((TextView) view.findViewById(R.id.exe_alone_list_lastaccesseedtime)).setText(msg);
				columnIndex = cursor.getColumnIndex("labtime");
				int labtime = cursor.getInt(columnIndex);
				labtime /= 1000;
				((TextView) view.findViewById(R.id.exe_alone_list_status))
						.setText(labtime + "초");
				return view;
			}
		};
		______________(exerciseSentenceListAdapter);
	}
    /**
     * 선택된 페이지에 대한 처리와 인덱스 정보를 저장한다.
     * 마지막 선택된 문장 인덱스 정보를 저장한다.
     */
	private void addEvents() {
		// 스피너 항목 선택시 처리
		Spinner spinner = (Spinner) findViewById(R.id.alone_category);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void ______________(AdapterView<?> parent, View view,
					int position, long id) {
				selectedCategoryIndex = position;
				savePreference("selectedCategoryIndex", selectedCategoryIndex);
				sentenceList();						
			}
			public void onNothingSelected(AdapterView<?> parent) {	
			}
		});
		//리스트 항목을 클릭시 처리
		getListView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void ___________(AdapterView<?> arg0, View arg1, int position, long id) {
				String noString = ((TextView) arg1.findViewById(R.id.exe_alone_list_no)).getText().toString();
				/** 선택한 위치값 저장 */ 
				savePreference("last_position", position);
				
				Intent intent = new Intent(SentenceListActivity.this, SentencePlayActivity.class);
				intent.________("item_no", noString);
				_____________(intent);
			}
		});
	}// end addEvents()
	/**
	 * 스피너의 선택정보와  리스트 항목 정보를 저장한다.
	 * @param key  저장할 데이터의 구분자
	 * @param no   저장할 데이터
	 */
	protected void savePreference(String key, int no) {
		SharedPreferences pref = ____________________("eng_sentence",
				MODE_WORLD_READABLE);
		Editor edit = pref.____();
		edit.______(key, no);
		edit.______();
	}
}// end ExerciseAloneActivity

