package org.turntotech.simplebackgroundservice;

import android.app.IntentService;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.SystemClock;
import android.widget.TextView;

public class SimpleIntentService extends IntentService {
    public static final String PARAM_IN_MSG = "in_msg";
    public static final String PARAM_OUT_MSG = "out_msg";
    
    public static final String ACTION_RESP = "org.turntotech.intent.action.MESSAGE_PROCESSED";
    
    int running_secs;
 
    public SimpleIntentService() {
        super("SimpleIntentService");
    }
 
    @Override
    protected void onHandleIntent(Intent intent) {
 
        System.out.println("SimpleIntentService Called");
    	String msg = intent.getStringExtra(PARAM_IN_MSG);
        int userInput = Integer.valueOf(msg);
        running_secs = userInput * 60; //change min to sec

        while(true){
            SystemClock.sleep(5000); // 5 seconds
            running_secs -= 5;

            String resultTxt = running_secs + " seconds remaining";
            Intent broadcastIntent = new Intent();

            // Set the general action to be performed.
            broadcastIntent.setAction(ACTION_RESP);

            // Add a new category to the intent. Categories provide additional detail about the action the intent performs.
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);

            // Add extended data to the intent.
            broadcastIntent.putExtra(PARAM_OUT_MSG, resultTxt);

            /*
             * Broadcast the given intent to all interested BroadcastReceivers.
             * This call is asynchronous; it returns immediately, and
             * you will continue executing while the receivers are run.
             */
            sendBroadcast(broadcastIntent);
            if(running_secs == 0){
                //source: https://developer.android.com/guide/topics/media/mediaplayer.html
                //This is my minimum viable product. Not ideal method, for the desired method see link above
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.la_vie_en_rose);
                mediaPlayer.start();
                break;
            }

        }//end while
    }
}