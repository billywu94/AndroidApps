
package org.turntotech.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import org.turntotech.notification.Log;
import org.turntotech.notification.MainActivity;
import org.turntotech.notification.R;

public class NotifyActivity extends Activity {

    private int NOTIFICATION_ID = 1001;

    private Notification.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("TurnToTech", "Project Name - Notification");
        TextView text = (TextView)findViewById(R.id.textView1);
        text.setText(R.string.notification);

        // 1. Cancel all existing notifications for the current application
        ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();

        // 2. Create a notification
        mBuilder = new Notification.Builder(this);

        // 3. Set the text (first row) of the notification
        mBuilder.setContentTitle("TurnToTech notification");


        // 4. Set icon for notification.
        mBuilder.setSmallIcon(R.drawable.ic_launcher);

        //5. We also want our application to open when the notification is clicked. For that we create a PendingIntent ( like we did for Alarm example)
        Intent resultIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntentToOpenApp = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Supply the PendingIntent to send when the notification is clicked.
        mBuilder.setContentIntent(pendingIntentToOpenApp);

        // 6. Set the vibration pattern to use.
        mBuilder.setVibrate(new long[] { 0, 500, 500, 500, 500, 500, 500 });

        //7. Set a notification sound for the notification
        Uri alarmSound = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);

    }


    //8. This method will be called when the Notify button is clicked. ( Configured via activity_main.xml)
    public void notifyClicked(View view) {
        //3.30.17 source:: http://stackoverflow.com/questions/13063902/two-different-layouts-for-one-activity

        //The NotificationManager is a system Service used to manage Notification. Get a reference to it using the getSystemService() method.
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        // Set the text (second row) of the notification
        mBuilder.setContentText("Note : "
                + DateFormat.format("MM/dd/yy h:mm:ss aa",
                System.currentTimeMillis()));

        //9. Trigger the notification
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());


    }


}