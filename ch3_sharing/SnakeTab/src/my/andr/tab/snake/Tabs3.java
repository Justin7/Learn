package my.andr.tab.snake;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.content.Intent;

@SuppressWarnings("deprecation")
public class Tabs3 extends TabActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final TabHost tabHost = getTabHost();
		
		tabHost.addTab(tabHost.newTabSpec("tab1")
				.setIndicator("list")
				.setContent(new Intent(this, List1.class)));
		
		// This tab sets the intent flag so that it is recreated each time
		// the tab is clicked.
		tabHost.addTab(tabHost.newTabSpec("tab2")
				.setIndicator("destroy")
				.setContent(new Intent(this, Controls2.class)
			    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

		tabHost.addTab(tabHost.newTabSpec("tab3")
				.setIndicator("photo list")
				.setContent(new Intent(this, List8.class)));
	}
}
