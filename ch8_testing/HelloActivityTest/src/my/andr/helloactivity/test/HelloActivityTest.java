package my.andr.helloactivity.test;

import my.andr.helloactivity.HelloActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;

public class HelloActivityTest extends ActivityInstrumentationTestCase2<HelloActivity> {
	private HelloActivity mActivity;
	private EditText mView;
	private String resourceString;
	
	public HelloActivityTest() {
		super(HelloActivity.class);
	}
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Log.e("log","HelloActivityTest.setUp()");
		mActivity = this.getActivity();
		mView = (EditText) mActivity.findViewById(my.andr.helloactivity.R.id.text);
		resourceString = mActivity.getString(my.andr.helloactivity.R.string.hello);
	}
	public void testText() {
		Log.e("log","HelloActivityTest.testText()");
		assertEquals(resourceString, mView.getText().toString());
	}
	public void testPreconditions() {
		Log.e("log","HelloActivityTest.testPreconditions()");
		assertNotNull(mView);
	}
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		Log.e("log","HelloActivityTest.tearDown()");
	}
}
