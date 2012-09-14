package my.andr.actionbar.tabs;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ActionBarTabs extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onAddTab(View v) {
		final ActionBar bar = getActionBar();
		final int tabCount = bar.getTabCount();
		final String text = "Tab " + tabCount;
		bar.addTab(bar.newTab().setText(text)
				.setTabListener(new TabListener(new TabContentFragment(text))));
	}

	public void onRemoveTab(View v) {
		final ActionBar bar = getActionBar();
		int count=bar.getTabCount() - 1;
		if(count>=0){
			bar.removeTabAt(count);
		}
	}

	public void onToggleTabs(View v) {
		final ActionBar bar = getActionBar();
		if (bar.getNavigationMode() == ActionBar.NAVIGATION_MODE_TABS) {
			bar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE, ActionBar.DISPLAY_SHOW_TITLE);
		} else {
			bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
		}
	}

	public void onRemoveAllTabs(View v) {
		getActionBar().removeAllTabs();
	}

	private class TabListener implements ActionBar.TabListener {
		private TabContentFragment mFragment;

		public TabListener(TabContentFragment fragment) {
			mFragment = fragment;
		}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.add(R.id.fragment_content, mFragment, mFragment.getText());
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(mFragment);
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			Toast.makeText(ActionBarTabs.this, "Reselected!",
					Toast.LENGTH_SHORT).show();
		}
	}

	private class TabContentFragment extends Fragment {
		private String mText;

		public TabContentFragment(String text) {
			mText = text;
		}

		public String getText() {
			return mText;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View fragView = inflater.inflate(R.layout.content, container, false);
			TextView text = (TextView) fragView.findViewById(R.id.text);
			text.setText(mText);
			return fragView;
		}
	}
}
