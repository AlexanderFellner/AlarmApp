package com.example.alarmwakeup;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;


public class AlarmActivity extends Activity {
    MediaPlayer mPlayer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);
		mPlayer=MediaPlayer.create(this,R.raw.peteralexanderhoney);
		mPlayer.start();
		Toast toast=Toast.makeText(this, "In OnCreate of AlarmActivity",Toast.LENGTH_LONG);
		toast.show();
	}
	public void Stop(View view){
		mPlayer.stop();
		Toast toast=Toast.makeText(this,"In player Stop",Toast.LENGTH_LONG);
		toast.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alarm, menu);
		return true;
	}
	public void Absenden(View view) {
		EditText edittext1=(EditText)findViewById(R.id.editText1);
		Editable editable1=edittext1.getText();
		String stundetext=editable1.toString();
		int stunde=0,minute=0,monat=0,tag=0;
		try {
			stunde=Integer.parseInt(stundetext);
		}
		catch(NumberFormatException ex1){
			 Toast toast1=Toast.makeText(this, "Falsches Format für stunde",Toast.LENGTH_LONG);
		     toast1.show();
		}
	    EditText edittext2=(EditText)findViewById(R.id.editText2);
		Editable editable2=edittext2.getText();
		String minutetext=editable2.toString();
		try {
			minute=Integer.parseInt(minutetext);
		}
		catch(NumberFormatException ex2){
			 Toast toast2=Toast.makeText(this, "Falsches Format für minute",Toast.LENGTH_LONG);
		     toast2.show();
		}
		EditText edittext3=(EditText)findViewById(R.id.editText3);
		Editable editable3=edittext3.getText();
		String monattext=editable3.toString();
		try {
				monat=Integer.parseInt(monattext);
			}
			catch(NumberFormatException ex2){
				 Toast toast3=Toast.makeText(this, "Falsches Format für monat",Toast.LENGTH_LONG);
			     toast3.show();
		}
		EditText edittext4=(EditText)findViewById(R.id.editText4);
		Editable editable4=edittext4.getText();
		String tagtext=editable4.toString();
		try {
				tag=Integer.parseInt(tagtext);
			}
			catch(NumberFormatException ex2){
				 Toast toast4=Toast.makeText(this, "Falsches Format für tag",Toast.LENGTH_LONG);
			     toast4.show();
		}
	    if(minute>=0 && minute<=60 && stunde>=0 && stunde<=60){
	    	Calendar calendar=Calendar.getInstance();
	    	long current_hour=calendar.get(Calendar.HOUR_OF_DAY);
	    	long current_minute=calendar.get(Calendar.MINUTE);
	    	Toast toast=Toast.makeText(this,"In Absenden,current_hour "+current_hour+" current_minute "
	    			+current_minute+" hour "+stunde+" minute "+minute,Toast.LENGTH_LONG);
	    	toast.show();
	    	calendar.set(Calendar.HOUR_OF_DAY,stunde);
	    	calendar.set(Calendar.MINUTE,minute);
	
	    	//long alarmhour=calendar.get(Calendar.HOUR_OF_DAY);
	    	//long alarmminute=calendar.get(Calendar.MINUTE);	    	
			//if (current_hour==alarmhour && current_minute==alarmminute){
	    	AlarmManager alarmmanager=(AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
	    	Intent intent=new Intent(this,AudioplayerActivity.class);
	    	intent.putExtra("AlarmWakeUpServiceMessage",true);
	    	intent.putExtra("Stunde",stunde);	    	
	    	intent.putExtra("Minute",minute);
	    	PendingIntent operation=PendingIntent.getActivity(this,-1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	    	
	    	    if(monat>=0 && monat<=11 && tag>=1 && tag<=31) {
	    	    	calendar.set(Calendar.MONTH,monat);
			    	calendar.set(Calendar.DAY_OF_MONTH,tag);
					
	    	    }
	    	    alarmmanager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),operation);
				toast.setDuration(1000);
	    	    toast=Toast.makeText(this,"alarm time=calendar.getTimeInMillis "+calendar.getTimeInMillis(),Toast.LENGTH_LONG);
	    	    toast.show();
	    	    //this.finish();
			//}
	       
	    }
    }
	public void onDestroy(){
		mPlayer.stop();
		mPlayer.release();
		super.onDestroy();
	}
	

}
