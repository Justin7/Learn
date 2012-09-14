package my.andr.spinner;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SpinnerActivity extends Activity {
    protected int mPos;
    protected String mSelection;
    protected ArrayAdapter<CharSequence> mAdapter;
    
    public static final int DEFAULT_POSITION = 2;
    public static final String PREFERENCES_FILE = "SpinnerPrefs";
    public static final String PROPERTY_DELIMITER = "=";
    public static final String POSITION_KEY = "Position";
    public static final String SELECTION_KEY = "Selection";
    public static final String POSITION_MARKER = POSITION_KEY + PROPERTY_DELIMITER;
    public static final String SELECTION_MARKER = SELECTION_KEY + PROPERTY_DELIMITER;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Spinner spinner = (Spinner) findViewById(R.id.Spinner01);
        mAdapter = ArrayAdapter.createFromResource(
        		this, 
        		R.array.Planets,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(this.mAdapter);
        OnItemSelectedListener spinnerListener = new myOnItemSelectedListener(this,mAdapter);
        spinner.setOnItemSelectedListener(spinnerListener);
        /*
         * To demonstrate a failure in the preConditions test,
         * uncomment the following line.
         * The test will fail because the selection listener for the
         * Spinner is not set.
         */
         // spinner.setOnItemSelectedListener(null);
    }
    public class myOnItemSelectedListener implements OnItemSelectedListener {
        ArrayAdapter<CharSequence> mLocalAdapter;
        Activity mLocalContext;

        public myOnItemSelectedListener(Activity c, ArrayAdapter<CharSequence> ad) {
          this.mLocalContext = c;
          this.mLocalAdapter = ad;
        }

        public void onItemSelected(AdapterView<?> parent, View v, int pos, long row) {
            SpinnerActivity.this.mPos = pos;
            SpinnerActivity.this.mSelection = parent.getItemAtPosition(pos).toString();

            TextView resultText = (TextView)findViewById(R.id.SpinnerResult);
            resultText.setText(SpinnerActivity.this.mSelection);
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // do nothing
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if (!readInstanceState(this)) setInitialState();

        Spinner restoreSpinner = (Spinner)findViewById(R.id.Spinner01);
        restoreSpinner.setSelection(getSpinnerPosition());
    }
    @Override
    public void onPause() {
        super.onPause();
        if (!writeInstanceState(this)) {
             Toast.makeText(this, "Failed to write state!", Toast.LENGTH_LONG).show();
        }
    }
    public void setInitialState() {
        this.mPos = DEFAULT_POSITION;
    }
    public boolean readInstanceState(Context c) {
        SharedPreferences p = c.getSharedPreferences(PREFERENCES_FILE, MODE_WORLD_READABLE);

        this.mPos = p.getInt(POSITION_KEY, SpinnerActivity.DEFAULT_POSITION);
        this.mSelection = p.getString(SELECTION_KEY, "");

        return (p.contains(POSITION_KEY));
    }
    public boolean writeInstanceState(Context c) {
        SharedPreferences p = c.getSharedPreferences(SpinnerActivity.PREFERENCES_FILE, MODE_WORLD_READABLE);
        SharedPreferences.Editor e = p.edit();

        e.putInt(POSITION_KEY, this.mPos);
        e.putString(SELECTION_KEY, this.mSelection);

        return (e.commit());
    }
    public int getSpinnerPosition() {
        return this.mPos;
    }
    public void setSpinnerPosition(int pos) {
        this.mPos = pos;
    }
    public String getSpinnerSelection() {
        return this.mSelection;
    }
    public void setSpinnerSelection(String selection) {
        this.mSelection = selection;
    }
}
