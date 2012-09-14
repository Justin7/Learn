package my.andr.fragment.hideshow;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FragmentHideShow extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		FragmentManager fm = getFragmentManager();
		addShowHideListener(R.id.frag1hide, fm.findFragmentById(R.id.fragment1));
		addShowHideListener(R.id.frag2hide, fm.findFragmentById(R.id.fragment2));
	}

	void addShowHideListener(int buttonId, final Fragment fragment) {
		final Button button = (Button) findViewById(buttonId);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.setCustomAnimations(android.R.animator.fade_in,
						android.R.animator.fade_out);
				if (fragment.isHidden()) {
					ft.show(fragment);
					button.setText("Hide");
				} else {
					ft.hide(fragment);
					button.setText("Show");
				}
				ft.commit();
			}
		});
	}

	public static class FirstFragment extends Fragment {
		TextView mTextView;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.labeled_text_edit, container, false);
			View tv = v.findViewById(R.id.msg);
			((TextView) tv).setText("The fragment saves and restores this text.");
			mTextView = (TextView) v.findViewById(R.id.saved);
			if (savedInstanceState != null) {
				mTextView.setText(savedInstanceState.getCharSequence("text"));
			}
			return v;
		}

		@Override
		public void onSaveInstanceState(Bundle outState) {
			super.onSaveInstanceState(outState);
			outState.putCharSequence("text", mTextView.getText());
		}
	}

	public static class SecondFragment extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.labeled_text_edit, container, false);
			View tv = v.findViewById(R.id.msg);
			((TextView) tv).setText("The TextView saves and restores this text.");
			// Retrieve the text editor and tell it to save and restore its state.
			// Note that you will often set this in the layout XML, but since
			// we are sharing our layout with the other fragment we will customize it here.
			((TextView) v.findViewById(R.id.saved)).setSaveEnabled(true);
			return v;
		}
	}
}
