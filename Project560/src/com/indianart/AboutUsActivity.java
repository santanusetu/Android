package com.indianart;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutUsActivity extends Activity implements OnClickListener {
	TextView tvLearnMore1,tvLearnMore2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
		tvLearnMore1=(TextView) findViewById(R.id.tvLearnMore1);
		tvLearnMore2=(TextView) findViewById(R.id.tvLearnMore2);
		tvLearnMore1.setOnClickListener(this);
		tvLearnMore2.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		
		case R.id.tvLearnMore1:
			Intent browserIntent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.indiaifa.org/india-foundation-arts.html"));
			startActivity(browserIntent1);
			break;
		case R.id.tvLearnMore2:
			Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.indiaifa.org/project560/project-560-found-spaces-initiative.html"));
			startActivity(browserIntent2);
			break;
			
		}
	}
}
