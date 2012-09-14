package my.andr.searchable.dict;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import android.content.res.Resources;

public class Dictionary {
	/** 단어와 설명을 쌍으로 저장하는 클래스*/
    public static class Word {
        public final String word;
        public final String definition;

        public Word(String word, String definition) {
            this.word = word;
            this.definition = definition;
        }
    }

    private static final Dictionary sInstance = new Dictionary();
    /** singleton pattern 구현에 의해  생성된 객체 리턴 */
    public static Dictionary getInstance() {
        return sInstance;
    }
    /**  시작 문자열과 그 시작 문자열로 시작되는 단어를  저장할 Map 선언*/
    private final Map<String, List<Word>> mDict = new ConcurrentHashMap<String, List<Word>>();

    private Dictionary() {
    }

    private boolean mLoaded = false;

    /** 파일로 부터 데이터가 읽어지지 않았다면  Thread 를 생성하여 파일을 읽도록 loadWords()를 호출한다 <br/>
     * Loads the words and definitions if they haven't been loaded already.
     *
     * @param resources Used to load the file containing the words and definitions.
     */
    public synchronized void ensureLoaded(final Resources resources) {
        if (mLoaded) return;
        new Thread() {
            public void run() {
                try {
                    loadWords(resources);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }
    /** 파일로 부터 데이터를 읽어  addWord()를 호출하여 저장한다.*/
    private synchronized void loadWords(Resources resources) throws IOException {
        if (mLoaded) return;

        InputStream inputStream = resources.openRawResource(R.raw.definitions);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String line;
            while((line = reader.readLine()) != null) {
                String[] strings = line.split("-");
                if (strings.length < 2) continue;
                addWord(strings[0].trim(), strings[1].trim());
            }
        } finally {
            reader.close();
        }
        mLoaded = true;
    }

    /** 전달된 파라메터의 문자열과 같은 문자열이 있다면 저장된 list를 리턴하고, 없으면 null 리턴.*/
    @SuppressWarnings("unchecked")
	public List<Word> getMatches(String query) {
        List<Word> list = mDict.get(query);
        return list == null ? Collections.EMPTY_LIST : list;
    }
    
    /** 단어의 시작 문자열과 일치하는 문자열이 있는지 addMatch()를 호출한다 .*/
    private void addWord(String word, String definition) {
        final Word theWord = new Word(word, definition);

        final int len = word.length();
        for (int i = 0; i < len; i++) {
            final String prefix = word.substring(0, len - i);
            addMatch(prefix, theWord);
        }
    }
    /** 존재하는 단어장에 일치하는 시작 문자열이 있다면 문자열의 List에 추가하고, 
     * 없다면 새로운 List를 만들어 Map에 추가한다  .*/
    private void addMatch(String prefix, Word word) {
        List<Word> matches = mDict.get(prefix);
        if (matches == null) {
            matches = new ArrayList<Word>();
            mDict.put(prefix, matches);
        }
        matches.add(word);
    }
}
