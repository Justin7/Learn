package my.andr.memorize.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EngSentenceDBHelper extends ________________ {


	public EngSentenceDBHelper(Context context) {
		super(context, "sentences.db", null ,1);
	}

	@Override
	public void ________(SQLiteDatabase db) {
		String[] engs={
			"Once you see all those animals, you should feel better.",
			"I need to fix my car as soon as possible.",
			"I hope it snows all day long tomorrow.",
			"Can I get you anything?",
			"How many apples did you eat today?",
			"If it is possible, I'd like to have a Diet Coke.",
			"Whose cellular phone is this anyway?",
			"Richard, what are you doing up there?",
			"Who is that? To tell the truth, I don't like him that much.",
			"One should keep one's promise; otherwise no one will trust him or her.",
			"Speaking of the tennis match, this is John's last one of the season.",
			"Do you know how to cook this?",
			"I feel sick to my stomach.",
			"Feel free to call me, whenever you want to play tennis.",
			"I have two sisters, and both of them are sick. To make matters worse, they have to take their final exams this week.",
			"How come you are not wearing a yellow uniform today?",
			"I have to make a decision by 10:30.",
			"How often do you play tennis?",
			"Do you mind if I give you some advice on that? It is a very important tip on how to use the court.",
			"You are much better than me.",
			"I can't believe this is the last summer camp that I am attending with you guys.",
			"There's some food on the table. Help yourself, but please don't feed the dog. We are trying to cut down the food cost.",
			"Is there a drug store around here?",
			"Don't tell me you can't go there.",
			"You should call the doctor right away.",
			"Would you bring more batteries for me?",
			"May I ask you where I can find those batteries? I looked around everywhere but I couldn't find them. Where did you put them?",
			"What is going on down there?",
			"I had a hard time controlling the boat.",
			"Please forgive me this time. I will make sure it won't happen again.",
			"On a day like this, I would do anything for a cold drink.",
			"What are you looking at?",
			"I heard you are moving to New York. Is that right?",
			"Is this the ring you were looking for?",
			"How much did you pay for that?",
			"How do you open this door?",
			"I hate this. This kind of thing makes me really angry.",
			"We are very sorry for all the trouble we've caused you, but that's the way it is around here. Take it or leave it.",		
			"Excuse me. How long will it take to fix it?",
			"Oh my gosh! What a mess! What happened here?",
			"John promised to visit Paul's office before he leaves for Egypt.",
			"Let me know when she comes in.",
			"Why don't you give her a call and remind her that I'll stop by around 9.",
			"I wonder if this pearl necklace will make her happy.",
			"Are you surprised at the news?",
			"Do you want me to verify that for you?",
			"It was here on the table this morning but it's gone now.",
			"It's obvious he failed the exam twice before he became a broadcaster.",
			"The reason why he failed the exam was he simply didn't study hard enough.",
			"If he studies hard, he will pass the exam. That's for sure."		
		};
		
		String[] kors={
			"일단 저 동물들을 보면 네 기분이 나아질 거야.",
			"가능한 한 빨리 내 차를 고쳐야 해.",
			"내일 하루 종일 눈이 왔으면 좋겠어.",
			"뭐 좀 가져다 드릴까요?",
			"너 오늘 사과를 몇 개나 먹었니?",
			"가능하다면 다이어트 콜라로 먹겠습니다.",
			"도대체 이것은 누구 핸드폰입니까?",
			"리처드, 그 위에서 뭐 하세요?",
			"저 사람이 누구지? 솔직히 말해, 나는 그를 그다지 좋아하지 않아요.",
			"사람은 약속을 지켜야 해. 그렇지 않으면 아무도 그 사람을 믿지 않을 거야.",
			"테니스 경기로 말하자면, 이것은 존의 그 시즌 마지막 경기입니다.",
			"이것을 어떻게 요리하는지 알고 있니?",
			"배가 아프네.",
			"테니스 경기를 하고 싶으면 사양하지 말고 언제든 내게 전화해요.",
			"저에게는 누나가 두 명 있는데 모두 아파요. 설상가상으로 그들은 이번 주에 학기말 시험을 쳐야 합니다.",
			"너는 오늘 왜 노란 유니폼을 입지 않았니?",
			"나는 10시 30분까지 결정을 내려야 해.",
			"얼마나 자주 테니스를 칩니까?",
			"제가 거기에 대해 조언을 좀 해도 될까요? 그것은 테니스 코트를 사용하는 데 매우 중요한 정보가 될 겁니다.",
			"당신이 저보다 훨씬 잘하시는군요.",
			"이것이 내가 너희들과 함께 참석하는 마지막 여름 캠프라니 믿어지지가 않는다.",
			"식탁 위에 음식이 좀 있어요. 마음껏 들되, 개에게는 먹이지 마세요. 음식 비용을 절감하려고 합니다.",
			"이 근처에 약국이 있나요?",
			"거기에 갈 수 없다는 말은 말아줘.",
			"당장 의사를 부르는 게 좋겠어.",
			"배터리 좀 더 가져다 줄래요?",
			"어디 가면 그런 배터리를 찾을 수 있는지 알려주시겠습니까? 사방을 다 뒤져봤는데 찾지 못했어요. 어디에 두셨어요?",
			"그 밑에 무슨 일이에요?",
			"그 배를 조종하는 데 매우 힘이 들었어요.",
			"한 번만 용서해주십시오. 다시는 그런 일이 없도록 하겠습니다.",
			"오늘같이 더운 날엔, 찬 음료수를 위해서라면 뭐든지 하겠어.",
			"뭘 보고 있니?",
			"뉴욕으로 이사한다고 들었는데, 그게 사실이냐?",
			"이것이 네가 찾고 있던 반지니?",
			"너 그것 얼마 주고 샀니?",
			"이 문을 어떻게 여니?",
			"난 이런 것은 딱 질색이야. 이런 일은 정말 나를 화나게 만든다구.",
			"여러 가지로 폐를 끼쳐서 정말 죄송하지만, 세상일이란 게 다 그렇지요. 사든지 말든지 하세요.",
			"실례합니다. 그것을 고치는 데 시간이 얼마나 걸릴까요?",
			"어휴, 엉망이네. 여기 무슨 일이에요?",
			"존은 이집트로 떠나기 전에 폴의 사무실을 방문하기로 약속했습니다.",
			"그녀가 들어오면 내게 알려줘.",
			"그녀에게 전화해서 내가 9시쯤 들를 거라고 말해줘.",
			"이 진주 목걸이가 그녀를 행복하게 해줄까 몰라.",
			"그 뉴스에 놀라셨나요?",
			"제가 그것을 확인해드릴까요?",
			"아침까지만 해도 여기 책상 위에 있었는데 지금은 없어졌네.",
			"그가 방송인이 되기 전에 그 시험에 두 번이나 떨어진 것이 분명해요.",
			"그가 그 시험에 떨어진 것은 단지 그가 공부를 열심히 하지 않았기 때문이에요.",
			"열심히만 공부한다면, 그는 그 시험에 합격할 겁니다. 그건 확실해요."
		};

		db._______("CREATE TABLE " + DBConstants.TABLE_NAME + " ( " +
				"	_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"	no INTEGER UNIQUE NOT NULL , " +
				" 	eng_sentence TEXT, " +
				"	kor_sentence TEXT," +
				"	labtime INTEGER," +
				"	last_accessed_time TEXT" +
				" ) ");
		ContentValues row = new ContentValues();
		for (int i = 0; i < kors.length; i++) {
			row.put("no", i+1);
			row.put("eng_sentence", engs[i]);
			row.put("kor_sentence", kors[i]);
			db.______(DBConstants.TABLE_NAME, null, row);
		}
	}//end onCreate

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db._______("DROP TABLE "+DBConstants.TABLE_NAME);
		________(db);
	}//end onUpgrade

}//end class
