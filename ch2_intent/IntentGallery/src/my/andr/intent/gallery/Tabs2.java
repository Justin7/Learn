package my.andr.intent.gallery;

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

public class Tabs2 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_main);
        
        final ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        
        bar.addTab(bar.newTab().setText("Tab 1")
				.setTabListener(new TabListener(new TabContentFragment("this is a tab"))));
        bar.addTab(bar.newTab().setText("Tab 2")
        		.setTabListener(new TabListener(new TabContentFragment("this is another tab"))));
        bar.addTab(bar.newTab().setText("Tab 3")
        		.setTabListener(new TabListener(new TabContentFragment("this is a third tab"))));
        
        if (savedInstanceState != null) {
            bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
    }
    private class TabListener implements ActionBar.TabListener {
		private TabContentFragment mFragment;

		public TabListener(TabContentFragment fragment) {
			mFragment = fragment;
		}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.add(R.id.tabs, mFragment, mFragment.getText());
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(mFragment);
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			Toast.makeText(Tabs2.this, "Reselected!",
					Toast.LENGTH_SHORT).show();
		}
	}

    public static class TabContentFragment extends Fragment {
		private String mText;

		public TabContentFragment(String text) {
			mText = text;
		}
		public TabContentFragment() {
			super();
		}

		public String getText() {
			return mText;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View fragView = inflater.inflate(R.layout.tab_content, container, false);
			TextView text = (TextView) fragView.findViewById(R.id.text);
			
			text.setText(mText);
			return fragView;
		}
	}
}