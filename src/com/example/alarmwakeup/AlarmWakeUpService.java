package com.example.alarmwakeup;

import android.app.*;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
public class AlarmWakeUpService extends Service {
  MediaPlayer mediaplayer;
  Updater updater;
  boolean runflag=false;
	@Override
	public IBinder onBind(Intent arg0) {
		
		return null;
	}
	@Override
	public void onCreate(){
		updater=new Updater();
		mediaplayer=MediaPlayer.create(this,R.raw.peteralexanderhoney);
		
	}
	@Override
	public int onStartCommand(Intent intent,int flags,int startid){
		super.onStartCommand(intent, flags, startid);
		runflag=true;
		mediaplayer.start();
		
		//updater.start();
		return START_NOT_STICKY;
	}
	private class Updater extends Thread {
		
		public void run(){
			
		}	
	}
	@Override
	public void onDestroy(){
		mediaplayer.stop();
		mediaplayer.release();
		super.onDestroy();
		
	}

}
