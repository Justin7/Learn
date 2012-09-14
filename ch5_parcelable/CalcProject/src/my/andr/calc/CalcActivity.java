package my.andr.calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CalcActivity extends Activity {
	boolean 첫숫자냐;
	TextView tv;
	Calc c;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        첫숫자냐=true;
        tv=(TextView)findViewById(R.id.tv);
        c=new Calc();
        
        OnClickListener su=new OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b=(Button)v;
				CharSequence cs=b.getText();
				if(첫숫자냐){
					tv.setText(cs);
					첫숫자냐=false;
				}else{
					tv.append(cs);
				}
			}
		};
		((Button)findViewById(R.id.b0)).setOnClickListener(su);
		((Button)findViewById(R.id.b1)).setOnClickListener(su);
		((Button)findViewById(R.id.b2)).setOnClickListener(su);
		((Button)findViewById(R.id.b3)).setOnClickListener(su);
		((Button)findViewById(R.id.b4)).setOnClickListener(su);
		((Button)findViewById(R.id.b5)).setOnClickListener(su);
		((Button)findViewById(R.id.b6)).setOnClickListener(su);
		((Button)findViewById(R.id.b7)).setOnClickListener(su);
		((Button)findViewById(R.id.b8)).setOnClickListener(su);
		((Button)findViewById(R.id.b9)).setOnClickListener(su);
		((Button)findViewById(R.id.bdot)).setOnClickListener(su);
		
		OnClickListener op=new OnClickListener() {
			@Override
			public void onClick(View v) {
				CharSequence scs=tv.getText();
				String ssu=scs.toString();
				
				Button b=(Button)v;
				CharSequence cs=b.getText();
				String sop=cs.toString();
				
				String r=c.calc(ssu, sop);
				
				tv.setText(r);
				첫숫자냐=true;
			}
		};
		((Button)findViewById(R.id.badd)).setOnClickListener(op);
		((Button)findViewById(R.id.bsub)).setOnClickListener(op);
		((Button)findViewById(R.id.bmul)).setOnClickListener(op);
		((Button)findViewById(R.id.bdiv)).setOnClickListener(op);
		((Button)findViewById(R.id.bequ)).setOnClickListener(op);
    }
}