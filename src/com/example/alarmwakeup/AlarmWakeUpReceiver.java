package com.example.alarmwakeup;

import java.util.Calendar;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.widget.EditText;

public class AlarmWakeUpReceiver extends BroadcastReceiver {

	//@SuppressLint("NewApi")
	@Override
	public void onReceive(Context context, Intent arg1) {
		PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "AlarmWakeUpReceiver");
        //wakeLock.acquire();
        Calendar calendar=Calendar.getInstance();
        long jetzt=System.currentTimeMillis();
		calendar.setTimeInMillis(jetzt);
		long current_hour_of_day=calendar.get(Calendar.HOUR_OF_DAY);
		
		AlarmManager alarmmanager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		
		calendar.set(Calendar.HOUR_OF_DAY,11);

		long hour_alarm=calendar.get(Calendar.HOUR_OF_DAY);
		//pm.wakeUp(calendar.getTimeInMillis());
		Intent intent=new Intent(context,AlarmActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
		PendingIntent operation=PendingIntent.getActivity(context,-1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	
		if(hour_alarm==current_hour_of_day)
			alarmmanager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),operation);
		Log.d("BootReceiver","OnReceived");
		//wakeLock.release();
		
	}
	
	

}
