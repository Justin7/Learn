package my.andr.ui.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AlertDialogSamples extends Activity {
	private static final int DIALOG_YES_NO_MESSAGE = 1;
	private static final int DIALOG_YES_NO_LONG_MESSAGE = 2;
	private static final int DIALOG_LIST = 3;
	private static final int DIALOG_SINGLE_CHOICE = 4;
	private static final int DIALOG_MULTIPLE_CHOICE = 5;
	private static final int DIALOG_TEXT_ENTRY = 6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/*
		 * Display a text message with yes/no buttons and handle each message as
		 * well as the cancel action
		 */
		Button twoButtonsTitle = (Button) findViewById(R.id.two_buttons);
		twoButtonsTitle.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG_YES_NO_MESSAGE, "dialog");
			}
		});

		/*
		 * Display a long text message with yes/no buttons and handle each
		 * message as well as the cancel action
		 */
		Button twoButtons2Title = (Button) findViewById(R.id.two_buttons2);
		twoButtons2Title.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG_YES_NO_LONG_MESSAGE, "dialog");
			}
		});

		/* Display a list of items */
		Button selectButton = (Button) findViewById(R.id.select_button);
		selectButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG_LIST, "dialog");
			}
		});

		/* Display a radio button group */
		Button radioButton = (Button) findViewById(R.id.radio_button);
		radioButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG_SINGLE_CHOICE, "dialog");
			}
		});

		/* Display a list of checkboxes */
		Button checkBox = (Button) findViewById(R.id.checkbox_button);
		checkBox.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG_MULTIPLE_CHOICE, "dialog");
			}
		});

		/* Display a text entry dialog */
		Button textEntry = (Button) findViewById(R.id.text_entry_button);
		textEntry.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG_TEXT_ENTRY, "dialog");
			}
		});
	}
	
	public void showDialog(int id, String tag) {
		DialogFragment newFragment = MyAlertDialogFragment.newInstance(id);
		newFragment.show(getFragmentManager(), tag);
	}

	public static class MyAlertDialogFragment extends DialogFragment {
		public static MyAlertDialogFragment newInstance(int id) {
			MyAlertDialogFragment frag = new MyAlertDialogFragment();
			Bundle args = new Bundle();
			args.putInt("id", id);
			frag.setArguments(args);
			return frag;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			int id = getArguments().getInt("id");
			switch (id) {
			case DIALOG_YES_NO_MESSAGE:
				return 
				new AlertDialog.Builder(getActivity())
					.setIcon(R.drawable.alert_dialog_icon)
					.setTitle(R.string.alert_dialog_two_buttons_title)
					.setPositiveButton(R.string.alert_dialog_ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
									/* User clicked OK so do some stuff */
							}
						}
					)
					.setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
									/* User clicked Cancel so do some stuff */
							}
						}
					).create();
			case DIALOG_YES_NO_LONG_MESSAGE:
				return 
				new AlertDialog.Builder(getActivity())
					.setIcon(R.drawable.alert_dialog_icon)
					.setTitle(R.string.alert_dialog_two_buttons_msg)
					.setMessage(R.string.alert_dialog_two_buttons2_msg)
					.setPositiveButton(R.string.alert_dialog_ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								/* User clicked OK so do some stuff */
							}
						}
					)
					.setNeutralButton(R.string.alert_dialog_something,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								/* User clicked Something so do some stuff */
							}
						}
					)
					.setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								/* User clicked Cancel so do some stuff */
							}
						}
					).create();
			case DIALOG_LIST:
				return 
				new AlertDialog.Builder(getActivity())
					.setTitle(R.string.select_dialog)
					.setItems(R.array.select_dialog_items,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								/* User clicked so do some stuff */
								String[] items = getResources().getStringArray(R.array.select_dialog_items);
								new AlertDialog.Builder(getActivity())
									.setMessage("You selected: " + which+ " , "+ items[which])
									.show();
							}
						}
					).create();
			case DIALOG_SINGLE_CHOICE:
				return 
				new AlertDialog.Builder(getActivity())
					.setIcon(R.drawable.alert_dialog_icon)
					.setTitle(R.string.alert_dialog_single_choice)
					.setSingleChoiceItems(R.array.select_dialog_items2, 0,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								/*
								 * User clicked on a radio button do some
								 * stuff
								 */
							}
						}
					)
					.setPositiveButton(R.string.alert_dialog_ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								/* User clicked Yes so do some stuff */
							}
						}
					)
					.setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								/* User clicked No so do some stuff */
							}
						}
					).create();
			case DIALOG_MULTIPLE_CHOICE:
				return 
				new AlertDialog.Builder(getActivity())
					.setIcon(R.drawable.ic_popup_reminder)
					.setTitle(R.string.alert_dialog_multi_choice)
					.setMultiChoiceItems(
						R.array.select_dialog_items3,
						new boolean[] { false, true, false, true, false, false, false },
						new DialogInterface.OnMultiChoiceClickListener() {
							public void onClick(DialogInterface dialog, int whichButton, boolean isChecked) {
								/* User clicked on a check box do some stuff */
							}
						}
					)
					.setPositiveButton(R.string.alert_dialog_ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								/* User clicked Yes so do some stuff */
							}
						}
					)
					.setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								/* User clicked No so do some stuff */
							}
						}
					).create();
			case DIALOG_TEXT_ENTRY:
				// This example shows how to add a custom layout to an AlertDialog
				LayoutInflater factory = LayoutInflater.from(getActivity());
				final View textEntryView = factory.inflate(R.layout.alert_dialog_text_entry, null);
				return 
				new AlertDialog.Builder(getActivity())
					.setIcon(R.drawable.alert_dialog_icon)
					.setTitle(R.string.alert_dialog_text_entry)
					.setView(textEntryView)
					.setPositiveButton(R.string.alert_dialog_ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								/* User clicked OK so do some stuff */
							}
						}
					)
					.setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								/* User clicked cancel so do some stuff */
							}
						}
					).create();
			}
			return null;
		}
	}
}
