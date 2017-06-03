/*
 * In this example a message is displayed every 5 seconds telling us 
 * how long the app has been running.
 */


package org.turntotech.simplebackgroundservice;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private BroadcastReceiver broadcastReceiver;
	private int value;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
        Log.i("TurnToTech", "Project Name - SimpleBackgroundService");
		final Intent msgIntent = new Intent(this, SimpleIntentService.class); //changed placement of code

		TextView timer = (TextView) findViewById(R.id.textView1);
		timer.setText(R.string.setTime);
		final EditText userInput = (EditText)findViewById(R.id.editText1);
		//source: https://guides.codepath.com/android/Basic-Event-Listeners#edittext-common-listeners
		userInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
					value = Integer.parseInt(userInput.getText().toString());
                    hideKeyboard(MainActivity.this);
					userInput.setFocusable(false); //prevents user from changing values after initial input
					//changed placement of code
					String ab = String.valueOf(value);
					// Add extended data to the intent.
					msgIntent.putExtra(SimpleIntentService.PARAM_IN_MSG, ab);

	    			/*
	    	 		* Request that a given application service be started.
	     			* The Intent should contain either the complete class
	     			* name of a specific service implementation to start or a
	     			* specific package name to target.
	     			*/
					startService(msgIntent);
					//end change placement
					return true;
			}
		});


	    // Base class for code that will receive intents sent by sendBroadcast().
		broadcastReceiver = new BroadcastReceiver(){
			@Override
			/*
			 * This method is called when the BroadcastReceiver is receiving an Intent broadcast.
			 * During this time you can use the other methods on BroadcastReceiver to view/modify
			 * the current result values.
			 */
			public void onReceive(Context arg0, Intent intent) {
			       String text = intent.getStringExtra(SimpleIntentService.PARAM_OUT_MSG);
			       Toast.makeText(getApplicationContext(),
							text, Toast.LENGTH_SHORT).show();
			}
	};
	    
	}
	
	/*
	 * Called after onPause(), for your activity to start interacting with the user.
	 */
	public void onResume()
	  {
	    super.onResume();
		
		IntentFilter filter = new IntentFilter(SimpleIntentService.ACTION_RESP);
	    filter.addCategory(Intent.CATEGORY_DEFAULT);

		registerReceiver(broadcastReceiver,filter);
	  }

	/*
	 * Called as part of the activity lifecycle when an activity is going into the background,
	 * but has not (yet) been killed. 
	 * (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	  public void onPause()
	  {
	    unregisterReceiver(broadcastReceiver);
	    super.onPause();
	  }
	//source: http://stackoverflow.com/questions/4165414/how-to-hide-soft-keyboard-on-android-after-clicking-outside-edittext
	public static void hideKeyboard(Activity activity) {
		InputMethodManager inputMethodManager =(InputMethodManager)activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}
	


}
