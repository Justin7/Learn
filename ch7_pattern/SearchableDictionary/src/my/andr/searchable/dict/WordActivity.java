package my.andr.searchable.dict;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;

/** 검색된 결과 단어를 보여주는 Activity <br/>
 * Displays a word and its definition.
 */
public class WordActivity extends Activity {
    private TextView mWord;
    private TextView mDefinition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word);

        mWord = (TextView) findViewById(R.id.word);
        mDefinition = (TextView) findViewById(R.id.definition);

        Intent intent = getIntent();

        String word = intent.getStringExtra("word");
        String definition = intent.getStringExtra("definition");

        mWord.setText(word);
        mDefinition.setText(definition);
    }
}
