package backgroundservice.turntotech.org.simplebackgroudservice;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

public class MainActivity extends Activity {
    Chronometer mChronometer;
    boolean startClock = false; //button has not been clicked yet
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //source: http://stackoverflow.com/questions/21981817/is-there-already-a-stopwatch-class-for-android-and-why-doesnt-my-implementation
        mChronometer = (Chronometer) findViewById(R.id.chronometer1);
    }


    /*
        1.
        startNewService method is called by clicking the 'Start Service' button.
        It is defined in activity_main.xml onClick event.
    */
    public void startNewService(View view) {

        // Start the  service
        // Service MyService needs to be added in AndroidManifest.xml
        startService(new Intent(this, MyService.class));
        //source: http://stackoverflow.com/questions/33023356/chronometer-start-on-initial-button-click
        if(!startClock){ //if button is clicked, begin timer
            mChronometer.setBase(SystemClock.elapsedRealtime());
            mChronometer.start();
            startClock = true;
        }
    }

    /*
        2.
        stopNewService method is called by clicking the 'Stop Service' button.
        It is defined in activity_main.xml onClick event.
    */
    public void stopNewService(View view) {

        // Stop the  service
        stopService(new Intent(this, MyService.class));
        mChronometer.stop();
        mChronometer.setBase(SystemClock.elapsedRealtime()); //reset clock
        startClock = false; //reset boolean value to false
    }

}
