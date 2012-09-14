package my.andr.hcgallery;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

/** This is a shell activity that hosts ContentFragment when the device screen
 * is smaller than "large".
 */
public class ContentActivity extends Activity {
  private int mThemeId = 0;

  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      
      if (getResources().getConfiguration().orientation
              == Configuration.ORIENTATION_LANDSCAPE) {
          // If the screen is now in landscape mode, we can show the
          // dialog in-line with the list so we don't need this activity.
          finish();
          return;
      }
      
      Bundle extras = getIntent().getExtras();
      if (extras != null) {
          // The activity theme is the only state data that the activity needs
          // to restore. All info about the content displayed is managed by the fragment
          mThemeId = extras.getInt("theme");
      } else if (savedInstanceState != null) {
          // If there's no restore state, get the theme from the intent
          mThemeId = savedInstanceState.getInt("theme");
      }
      if (mThemeId != 0) {
          setTheme(mThemeId);
      }
      setContentView(R.layout.content_activity);
      if (extras != null) {
        // Take the info from the intent and deliver it to the fragment so it can update
        int category = extras.getInt("category");
        int position = extras.getInt("position");
        ContentFragment frag = (ContentFragment) getFragmentManager().findFragmentById(R.id.content_frag);
        frag.updateContentAndRecycleBitmap(category, position);
      }
  }
  @Override
  protected void onSaveInstanceState(Bundle outState) {
      super.onSaveInstanceState(outState);
      outState.putInt("theme", mThemeId);
  }
}
