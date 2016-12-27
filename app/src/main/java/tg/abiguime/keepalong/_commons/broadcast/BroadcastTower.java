package tg.abiguime.keepalong._commons.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import tg.abiguime.keepalong.main.home.StepService;

/**
 * By abiguime on 25/12/2016.
 * email: 2597434002@qq.com
 */

public class BroadcastTower extends BroadcastReceiver {

    public static String START_STEP_COUNTER = "tg.abiguime.keepalong._commons.broadcast.BroadcastTower.START_STEP_COUNTER";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(START_STEP_COUNTER)) {
            Intent stepService = new Intent(context, StepService.class);
            context.startService(stepService);
        }
    }
}
