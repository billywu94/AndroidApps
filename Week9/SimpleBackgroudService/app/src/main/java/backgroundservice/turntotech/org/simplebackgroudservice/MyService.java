package backgroundservice.turntotech.org.simplebackgroudservice;

/**
 * Created by RAJAT on 6/25/2015.
 */
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    String tag="TestService";
    Chronometer mChronometer;
    Timer timer;
    private static final int LOC_API_CALL_INTERVAL = 1000; // 1 second intervals
    private long seconds;
    private long minutes;
    private long hours;
    public MyService() {
        Log.e(tag,"Under TurnToTech Service");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return  null;
    }

    @Override
    public void onCreate() {


        //1.1 Service created.
        Toast.makeText(this, "The TurnToTech Service was Created.", Toast.LENGTH_LONG).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        // 1.2 called from Main Activity - startNewService
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.

        //source: http://stackoverflow.com/questions/35622084/how-to-add-chronometer-inside-service
        mChronometer = new Chronometer(MyService.this);
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();
        startTimer();

        // For time consuming an long tasks you can launch a new thread here...
        Toast.makeText(this, "TurnToTech Service Started.", Toast.LENGTH_LONG).show();

        return START_STICKY;
    }



    @Override
    public void onDestroy() {

        /*
        2.2
        onDestroy Called by the system to notify a Service that it is no longer used and is being removed.
        when stopNewService called from Main Activity,  it calls stopService. So no service is running
        at that moment and so onDestroy event is invoked.
        */

        stopTimer();
        // Service destroyed.
        Toast.makeText(this, "Time ran for " + hours + " hrs: " + minutes +" mins: " + seconds + " secs.",Toast.LENGTH_LONG).show();
    }

    private void startTimer(){
        if(timer!=null ){
            return;
        }
        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                long millis=SystemClock.elapsedRealtime() - mChronometer.getBase();
                seconds = (millis / 1000) % 60;
                minutes = (millis / (1000 * 60)) % 60;
                hours = (millis / (1000 * 60 * 60)) % 24;
                Log.e("timefortest", "" + seconds);
            }
        }, LOC_API_CALL_INTERVAL, LOC_API_CALL_INTERVAL);
    }

    private void stopTimer(){

        if(null!=timer){
            timer.cancel();
            timer=null;
        }
    }
}