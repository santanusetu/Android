package com.santanu.chak.tcabpcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity 
{

	Button batSec, bowlSec;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		batSec = (Button)findViewById(R.id.btBattingSecond);
		bowlSec = (Button)findViewById(R.id.btBowlingSecond);
		
		batSec.setOnClickListener(new View.OnClickListener()
		{	
			@Override
			public void onClick(View v) {
				
				Intent iBatS = new Intent(MainActivity.this, BattingSecond.class);
				startActivity(iBatS);
			}
		});
		
		bowlSec.setOnClickListener(new View.OnClickListener() 
		{	
			@Override
			public void onClick(View v) {

				Intent iBowlS = new Intent(MainActivity.this, BowlingSecond.class);
				startActivity(iBowlS);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
