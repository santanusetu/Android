package com.santanu.chakb.flashdemolight;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity
{
	ImageButton btnSwitch;
	private Camera camera;
	private boolean isFlashOn=false;
	private boolean hasFlash;
	Parameters params;
	MediaPlayer mp;
	ImageView volButt;
	private boolean isSoundOn = true;
	
	//clock
	private TextView date;
	boolean isClockOn=true;
	ImageView clockButt;
	LinearLayout clockLinLay;
	
	//location
	ImageView globeButt;
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// hide titlebar of application must be before setting the layout
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// hide statusbar of Android could also be done later
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);

		// flash switch button
		btnSwitch = (ImageButton) findViewById(R.id.ibSwitch);
		//vol button
		volButt = (ImageView) findViewById(R.id.ivVol);
		//time display 
		date = (TextView)findViewById(R.id.tvDate);
		clockButt=(ImageView)findViewById(R.id.ivClock);
		clockLinLay=(LinearLayout)findViewById(R.id.llDateTime);
		
		//google map view
		globeButt =  (ImageView)findViewById(R.id.ivGlobe);
		
		    
		// First check if device is supporting flashlight or not
		hasFlash = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

		if (!hasFlash) 
		{
			// device doesn't support flash -- Show alert message and close the application
			AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
			alert.setTitle("Error");
			alert.setMessage("Sorry, your device doesn't support Torch Light !");
			alert.setButton("OK", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int which) 
				{
					// closing the application
					finish();
				}
			});
			alert.show();
			return;
		}

		// get the camera
		getCamera();
		// displaying button image
		toggleButtonImage();
		
		// for display of clock
		displayClock();
				    
				    
		// Switch button click event to toggle flash on/off
		btnSwitch.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				if (isFlashOn) 
				{
					// turn off flash
					turnOffFlash();
				} else {
					// turn on flash
					turnOnFlash();
				}
			}
		});

		volButt.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				if (isSoundOn)
				{
					isSoundOn = false;
					volButt.setBackgroundResource(R.drawable.vol_off);
				} else {
					isSoundOn = true;
					volButt.setBackgroundResource(R.drawable.vol_on);
				}

			}
		});
		
		clockButt.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				if (isClockOn)
				{
					isClockOn = false;
					clockLinLay.setVisibility(View.INVISIBLE);
				//	clockButt.setBackgroundResource(R.drawable.hourglass_off);
				toggleHourGlassImage();
				} else {
					isClockOn = true;
					clockLinLay.setVisibility(View.VISIBLE);
				//	clockButt.setBackgroundColor(R.drawable.hourglass_new);
				toggleHourGlassImage();
				}

			}
		});
		
		globeButt.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{	
				i = new Intent(MainActivity.this, FindLocation.class);
            	startActivity(i); 
			}
		});
		
	}

	// Get the camera
	private void getCamera() 
	{
		if (camera == null) 
		{
			try {
				camera = Camera.open();
				params = camera.getParameters();
			} catch (RuntimeException e) {
				Log.e("Camera Error. Failed to Open. Error: ", e.getMessage());
			}
		}
	}

	// Turning On flash
	private void turnOnFlash() 
	{
		if (!isFlashOn)
		{
			Log.i("MainActivity-- turnonflash", "@@@@@@@@@ Camera value :: "+camera);
			if (camera == null || params == null) 
			{
				return;
			}
			// play sound
			playSound();

			params = camera.getParameters();
			params.setFlashMode(Parameters.FLASH_MODE_TORCH);
			camera.setParameters(params);
			camera.startPreview();
	
			isFlashOn = true;

			// changing button/switch image
			toggleButtonImage();
		}

	}

	// Turning Off flash
	private void turnOffFlash()
	{
		if (isFlashOn) 
		{
			if (camera == null || params == null) {
				return;
			}
			// play sound
			playSound();

			params = camera.getParameters();
			params.setFlashMode(Parameters.FLASH_MODE_OFF);
			camera.setParameters(params);
			camera.stopPreview();
			
			isFlashOn = false;
			
			// changing button/switch image
			toggleButtonImage();
		}
	}

	// Playing sound
	// will play button toggle sound on flash on / off
	private void playSound()
	{
		if (isSoundOn)
		{
			if (isFlashOn) {
				mp = MediaPlayer.create(MainActivity.this,R.raw.light_switch_off);
			} else {
				mp = MediaPlayer.create(MainActivity.this,R.raw.switch_sound);
			}
			
			mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() 
			{
				@Override
				public void onCompletion(MediaPlayer mp) {
					// TODO Auto-generated method stub
					mp.release();
				}
			});

			mp.start();

		}
	}
	// display time
	private void displayClock() 
	{
		if(isClockOn)
		{
		// for display of date time
		 final Calendar c = Calendar.getInstance();
		 int   yy = c.get(Calendar.YEAR);
		 int   mm = c.get(Calendar.MONTH);		
		 
		 SimpleDateFormat month_date = new SimpleDateFormat("MMM");
		 String month_name = month_date.format(c.getTime());
		 int   dd = c.get(Calendar.DAY_OF_MONTH);	
		 
		 SimpleDateFormat week_day = new SimpleDateFormat("EEEE");
		 String day = week_day.format(c.getTime());
		 
		 String space= "   ";
		 
		    // set current date into textview
		    date.setText(new StringBuilder().append("||").append(space).append(month_name).append(space).append(dd).append(space).append("||").
		    		append(space).append(day).append(space).append("||").append(space).append(yy).append(space).append("||"));
		    // Month is 0 based, just add 1
		}else{
			Log.i("MainActivity--clock", "Clock is closed");
		}
	}

	/*
	 * Toggle switch button images changing image states to on / off
	 */
	private void toggleButtonImage() 
	{
		if (isFlashOn) {
			btnSwitch.setImageResource(R.drawable.btn_switch_on);
		} else {
			btnSwitch.setImageResource(R.drawable.btn_switch_off);
		}
	}

	//hourglass image
	private void toggleHourGlassImage() 
	{
		if (isClockOn) 
		{
			clockButt.setImageResource(R.drawable.hourglass_new);
		} else {
			clockButt.setImageResource(R.drawable.hourglass_off);
		}
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();

		// on starting the app get the camera params
		getCamera();
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();

		// on resume turn on the flash
		/*if (hasFlash) {
			turnOnFlash();
		}*/
	}
	
	@Override
	protected void onPause() 
	{
		super.onPause();

		// on pause turn off the flash
		turnOffFlash();
		
		//System.out.println("@@@@@@@@@@@@@@@@@@@@ camera value before :: "+camera);
        Log.i("MainActivity--onPause", "@@@@@@@@@@@@@@@@@@@@ camera value BEFORE :: "+camera);
		
        if(camera!=null)
        {
            camera.stopPreview();
            camera.setPreviewCallback(null);
            camera.release();
            camera = null;
        }
		
		Log.i("MainActivity--onPause", "@@@@@@@@@@@@@@@@@@@@ camera value AFTER :: "+camera);
	}
	
	@Override
	protected void onStop()
	{
		super.onStop();
		
		// on stop release the camera
		if (camera != null) 
		{
			camera.release();
			camera = null;
		}
	}
	
	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}


}
