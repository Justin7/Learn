package my.andr.memorize;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

import my.andr.memorize.db.EngDbAdapter;
import my.andr.memorize.db.SentenceVO;
import my.andr.memorize.layout.AutoLinearLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SentencePlayActivity extends Activity  {
	private final  Context cxt=this;
	final static int SUCCESS_SENTENCE = 0;
	final static int ERROR_SENTENCE = 1;
	final static int SHOW_HINT = 2;
	private SentenceVO sentence;
	private TextView clickedSentence;
	private String[] splitSentence;
	private Button tryagainBtn;
	private Button memorizeBtn;
	private int sentenceClickCount;
	private long startTime;// 시작시간
	private long endTime;// 종료시간
	private long labTime;// 걸린시간

	private Animation playAnim;
	private EngDbAdapter dao=EngDbAdapter.getInstance(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercise_alone_play);

		clickedSentence = (TextView) findViewById(R.id.play_sentence_clicked);
		clickedSentence.setText("");
		addEvent();
		String item_no = getIntent().getStringExtra("item_no");
		sentence = dao.getSentence(item_no);
		((TextView) findViewById(R.id.play_sentence1)).setText(sentence.getKorSentence());
		((TextView) findViewById(R.id.play_sentence2)).setText(sentence.getEngSentence());
		//
		int lastAccTime = new Integer(sentence.getLabtime());
		lastAccTime /= 1000;
		((TextView) findViewById(R.id.play_last_accessed_time)).setText(lastAccTime + "초");
		//
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		final int today = new Integer(sdf.format(new Date()));
		int lat = 0;
		try {
			lat = new Integer(sentence.getLastAccessedTime());
		} catch (Exception e) {
		}
		int diff = lat - today;
		// System.out.println("DIFF="+diff);
		String msg = (lat == 0) ? "기록없음" : (diff < 0) ? diff * -1 + "일전" : "오늘";
		((TextView) findViewById(R.id.play_labtime)).setText(msg);
		setExerciseTitle("NO." + item_no);// 제목
		playAnim = AnimationUtils.loadAnimation(cxt,R.anim.play);
	}// end onCreate()
	
	/**
	 * 단어 클릭시 문장에 맞는지 확인하여 처리하는 메서드
	 */
	private OnClickListener sentenceListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (sentenceClickCount == splitSentence.length)
				return;
			String text = ((TextView) v).getText().toString();
			if (splitSentence[sentenceClickCount].equals(text)) {
				clickedSentence.append(" " + text);
				sentenceClickCount++;
				playAnim = AnimationUtils.loadAnimation(cxt, R.anim.play);
				v.setAnimation(playAnim);// 맞게 눌려진것에 대해 애니메이션 적용
				v.setVisibility(View.INVISIBLE);// 맞게눌린것은 안보이게 함.
				if (sentenceClickCount == splitSentence.length) {// 문장을 다 맞춘것
					endTime = new Date().getTime();
					labTime = endTime - startTime;
					handler.sendEmptyMessage(SUCCESS_SENTENCE);
					// 만약 기록이 처음이거나 기록갱신이면 저장
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					String todayDateFormat = sdf.format(new Date());
					if (sentence.getLabtime() == 0 || // 처음등록하는경우
							sentence.getLastAccessedTime() == null || // 처음등록하는경우
							!sentence.getLastAccessedTime().equals(
									todayDateFormat) || // 마지막날짜가 오늘이 아니거나
							labTime < sentence.getLabtime()) { // 기록 갱신되거나
						sentence.setLabtime((int) labTime);
						sentence.setLastAccessedTime(todayDateFormat);
						dao.updateLabtime(labTime, todayDateFormat,sentence.getNo());
					}
				}
			} else {
				handler.sendEmptyMessage(ERROR_SENTENCE);
			}
		}// end onClick
	};
	/**
	 * 결과 메세지를 받아 처리하는 핸들러 클래스
	 * 단어 클릭시 문장에 맞는지 확인하여 처리하는 메서드의 결과 메세지
	 */
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(cxt);
			switch (msg.what) {
				case ERROR_SENTENCE:
					vibrate(100);
					vibrate(100);
					dialog.setMessage("문장 순서가 틀렸습니다.")
							.setPositiveButton("확인", null).show();
					break;
				case SUCCESS_SENTENCE:
					String message = "";
					message += sentence.getLabtime() > labTime ? "기록갱신~~ " : "";
					message += "입력시간은 " + (labTime / 1000) + "초입니다.\n";
					boolean lastSentence = sentence.getNo() % 10 == 0;
					if (lastSentence) {// 단계별 마지막 문장인 경우
						message += "지금 단계의 마지막 문장입니다";//
					} else {
						message += "다음 문장으로 넘어갑니다.";
					}// end if
					
					if (sentence.getNo() != 50)
						dialog.setPositiveButton(lastSentence ? "다음 단계" : "다음 문장",
								onClickNextListener);
					else
						dialog.setPositiveButton("목록보기", onClickCloseListener);
						dialog.setMessage(message)
							.setNegativeButton("연습 다시", retryOnClickListener)
							.show();
					break;
				case SHOW_HINT:
					showHint();
					break;
			}// end switch
		}// end handleMessage

		private void vibrate(long interval) {
			Vibrator vibrator = (Vibrator) getApplicationContext()
					.getSystemService(Context.VIBRATOR_SERVICE);
			vibrator.vibrate(interval);
		}
	};


	private void setExerciseTitle(String title) {
		((TextView) findViewById(R.id.play_title)).setText(title);
	}

	/**
	 * 다시 재시도
	 */
	DialogInterface.OnClickListener retryOnClickListener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			resetSentence();
		}// end onCㅣick
	};	
	private void resetSentence() {
		// 클릭수 리셋
		sentenceClickCount = 0;
		startTime = Calendar.getInstance().getTimeInMillis();// 시작시간리셋
		// 입력된 문장 삭제
		clickedSentence.setText("");
		// 단어재정렬
		AutoLinearLayout ll = (AutoLinearLayout) findViewById(R.id.play_random_sentences);
		ll.removeAllViews();
		splitSentence();
	}
	
	DialogInterface.OnClickListener onClickNextListener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			Intent intent = new Intent(cxt,SentencePlayActivity.class);
			intent.putExtra("item_no", sentence.getNo() + 1 + "");
			startActivity(intent);
			finish();// 팝업삭제
		}// end onCㅣick
	};
	DialogInterface.OnClickListener onClickCloseListener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			finish();// 팝업삭제
		}// end onCㅣick
	};

	private void addEvent() {
		findViewById(R.id.play_title).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				handler.sendEmptyMessage(SHOW_HINT);
			}
		});
		tryagainBtn = (Button) findViewById(R.id.play_btn_tryagain);
		tryagainBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				resetSentence();
			}

		});

		memorizeBtn = (Button) findViewById(R.id.play_btn_memorize);
		memorizeBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				findViewById(R.id.play_sentence1).setVisibility(View.GONE);
				findViewById(R.id.play_sentence2).setVisibility(View.GONE);
				tryagainBtn.setVisibility(View.VISIBLE);
				memorizeBtn.setVisibility(View.GONE);
				sentenceClickCount = 0;
				splitSentence();
				startTime = new Date().getTime();// 시작시간
			}
		});
		findViewById(R.id.play_left_arrow).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						if (sentence.getNo() == 1)
							return;// 처음 문장
						Intent intent = new Intent(cxt,
								SentencePlayActivity.class);
						intent.putExtra("item_no", sentence.getNo() - 1 + "");
						startActivity(intent);
						finish();//
					}
				});
		findViewById(R.id.play_right_arrow).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						if (sentence.getNo() == 50)
							return;// 마지막 문장
						Intent intent = new Intent(cxt,
								SentencePlayActivity.class);
						intent.putExtra("item_no", sentence.getNo() + 1 + "");
						startActivity(intent);
						finish();//
					}
				});
	}
	/**
	 *  영어문장을 빈칸을 기준으로 잘라서 단어하나를 TextView에 담아 추가시킴.
	 */
	protected void splitSentence() {
		AutoLinearLayout ll = (AutoLinearLayout) findViewById(R.id.play_random_sentences);
		// ll.setBackgroundColor(Color.GRAY);
		splitSentence = sentence.getEngSentence().split(" ");
		ArrayList<String> al = new ArrayList<String>();
		Collections.addAll(al, splitSentence);

		while (al.size() != 0) {
			String text = al.remove(new Random().nextInt(al.size()));// 배열에서 임의로 하나씩 꺼내면서 지운다.
			TextView tv = new TextView(cxt);
			tv.setText(text);
			tv.setTextSize(24.0f);
			tv.setTextColor(Color.BLACK);
			tv.setBackgroundResource(R.drawable.my_rounded_corner);
			if (text.length() == 1) {
				tv.setPadding(15, 0, 15, 0);
			} else if (text.length() == 2) {
				tv.setPadding(10, 0, 10, 0);
			}
			tv.setOnClickListener(sentenceListener);
			AutoLinearLayout.LayoutParams layoutParams = new AutoLinearLayout.LayoutParams(	15, 15);
			ll.addView(tv, layoutParams);
		}// end for
	}// end splitSentence()

	private void debug(Object obj) {
		String string = (obj == null) ? "null" : obj.toString();
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT)
				.show();
	}

	private void showHint() {
		new AlertDialog.Builder(SentencePlayActivity.this)
				.setMessage(
						sentence.getKorSentence() + "\n"
								+ sentence.getEngSentence())
				.setPositiveButton("확인", null).show();
	}

}// end SentencePlayActivity
