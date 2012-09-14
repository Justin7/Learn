package my.andr.intent.gallery;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class HelloGallery extends Activity {
	private static final int MUSIC = 0;
	private static final int LOTTO = 1;
	private static final int TAB2 = 2;
	private static final int TAB3 = 3;
	private static final int DIAL = 4;
	private static final int WEB = 5;
	private static final int ETC1 = 6;
	private static final int ETC2 = 7;

	int[] index = { MUSIC, LOTTO, TAB2, TAB3, DIAL, WEB, ETC1, ETC2 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Gallery g = (Gallery) findViewById(R.id.gallery);
		g.setAdapter(new ImageAdapter(this));
		g.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Toast.makeText(HelloGallery.this, v.getId() + ":" + position + ":" + id, Toast.LENGTH_SHORT).show();
				switch ((int) id) {
					case MUSIC:
						Intent i = new Intent();
						ComponentName n = new ComponentName(
							"my.andr.svc.audio",
							"my.andr.svc.audio.AudioAct");
						i.setComponent(n);
						startActivity(i);
						break;
					case LOTTO:
						// ..
						break;
					case TAB2:
						// ..
						break;
					case TAB3:
						// ..
						break;
					case DIAL:
						// ..
						break;
					case WEB:
						// ..
						break;
					default:
						// ..
				}
			}
		});
	}

	public class ImageAdapter extends BaseAdapter {
		private Context mContext;

		private Integer[] mImageIds = { R.drawable.sample_1,
				R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4,
				R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7 };

		public ImageAdapter(Context c) {
			mContext = c;
		}

		public int getCount() {
			return mImageIds.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return index[position];
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView i = new ImageView(mContext);
			i.setImageResource(mImageIds[position]);
			i.setLayoutParams(new Gallery.LayoutParams(100, 70));
			i.setScaleType(ImageView.ScaleType.FIT_XY);
			return i;
		}
	}
}
