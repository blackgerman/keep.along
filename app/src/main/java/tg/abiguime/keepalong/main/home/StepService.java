package tg.abiguime.keepalong.main.home;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import tg.abiguime.keepalong._commons.broadcast.BroadcastTower;
import tg.abiguime.keepalong.main.MainActivity;

/**
 * By abiguime on 25/12/2016.
 * email: 2597434002@qq.com
 */

public class StepService extends Service implements SensorEventListener {


    public static final String ACTION = "tg.abiguime.keepalong.main.home.StepService";


    /* Sensor init */
    SensorManager sensorManager;


    private int currentStepCount;

    private StepServiceBinder binder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        if (binder == null)
            binder = new StepServiceBinder();
        return binder;
    }



    public class StepServiceBinder extends Binder {
        public int getCurrentStepCount (){
            return currentStepCount;
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        initSensor();
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
        }
        return START_STICKY;
    }


    private void initSensor() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        // get actual steps since the last reboot
        int actualStepsCount = (int) event.values[0];
         /*
         every time there is a footstep broadcast, save it if there is no other
         with the data and time
            - if the following one is still in the same day, but later, and lower,
            just keep on adding it to the former one before giving result.
            - but the current progression should also be saved somewhere,
                    showing when is the last time this new time has started to count
            10h09 : 230 pas
            11h30: 24 pas... second entry is from 11h 30 24 which is increasing again and again.
            each time we are rebooting, we take the last entry of the day, and we just set it as done
                // if the rebooting is forced, then, we just create a new track according to the last time thing
                // and we save it.
         */
        currentStepCount = actualStepsCount;
        Intent in = new Intent(MainActivity.StepCountReceiver.STEP_CHANGE);
        in.putExtra("step", currentStepCount);
        sendBroadcast(in);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        // send a broadcast when the service is destroyed
        // so that it could be recreated
        Intent in = new Intent(BroadcastTower.START_STEP_COUNTER);
        sendBroadcast(in);
    }
}
