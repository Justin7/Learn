package my.andr.permission.invoke;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PermissionActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button b = (Button)findViewById(R.id.button);
        b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				ComponentName cn=new ComponentName(
					"my.andr.permission.define",
					"my.andr.permission.define.PermissionNextActivity");
				i.setComponent(cn);
				startActivity(i);
			}
		});
    }
}