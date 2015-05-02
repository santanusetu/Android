package com.indianart;

import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.PushService;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	LinearLayout linAboutUs,linArtists,linEvents,linVenues,linPhotos,linContactUs,linFacebook,linTwitter,linYoutube;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		linAboutUs=(LinearLayout) findViewById(R.id.linAboutUs);
		linArtists=(LinearLayout) findViewById(R.id.linArtists);
		linEvents=(LinearLayout) findViewById(R.id.linEvents);
		linVenues=(LinearLayout) findViewById(R.id.linVenues);
		linPhotos=(LinearLayout) findViewById(R.id.linPhotos);
		linContactUs=(LinearLayout) findViewById(R.id.linContactUs);
		linFacebook=(LinearLayout) findViewById(R.id.linFacebook);
		linTwitter=(LinearLayout) findViewById(R.id.linTwitter);
		linYoutube=(LinearLayout) findViewById(R.id.linYoutube);
		
		// Push Notification Start
				ParseAnalytics.trackAppOpened(getIntent());

				// inform the Parse Cloud that it is ready for notifications
				PushService.setDefaultPushCallback(getApplicationContext(), MainActivity.class);
				ParseInstallation.getCurrentInstallation().saveInBackground();
				
		linAboutUs.setOnClickListener(this);
		linArtists.setOnClickListener(this);
		linEvents.setOnClickListener(this);
		linVenues.setOnClickListener(this);
		linPhotos.setOnClickListener(this);
		linContactUs.setOnClickListener(this);
		linFacebook.setOnClickListener(this);
		linTwitter.setOnClickListener(this);
		linYoutube.setOnClickListener(this);
	}

	
	/*public void callContactUs()
	{

		final Dialog dialog1 = new Dialog(this);
		dialog1.setCanceledOnTouchOutside(false);
		dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog1.setContentView(R.layout.contact_dialog);

		LinearLayout contact = (LinearLayout) dialog1.findViewById(R.id.linContact);
		LinearLayout learnMore = (LinearLayout) dialog1.findViewById(R.id.linLearnMore);
		
		
		contact.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent browserIntent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.indiaifa.org/india-foundation-arts.html"));
				startActivity(browserIntent1);
				dialog1.dismiss();
			}

		});
		
		learnMore.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.indiaifa.org/project560/project-560-found-spaces-initiative.html"));
				startActivity(browserIntent2);
				
				dialog1.dismiss();
			}

		});

		dialog1.show();
	}*/
	public void callEvents()
	{

		final Dialog dialog1 = new Dialog(this);
		dialog1.setCanceledOnTouchOutside(false);
		dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog1.setContentView(R.layout.events_dialog);

		LinearLayout prevEvents = (LinearLayout) dialog1.findViewById(R.id.linPrevEvents);
		LinearLayout upEvents = (LinearLayout) dialog1.findViewById(R.id.linUpEvents);
		
		
		prevEvents.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent5 = new Intent(getApplicationContext(),
						EventsActivity.class);
				startActivity(intent5);
				GlobalData.chkEvent=1;
				dialog1.dismiss();
			}

		});
		
		upEvents.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				/*Intent intent5 = new Intent(getApplicationContext(),
						EventsActivity.class);
				startActivity(intent5);
				GlobalData.chkEvent=2;*/
				
				Toast.makeText(getApplicationContext(), "No Information available about Upcoming Events now", Toast.LENGTH_SHORT).show();
				dialog1.dismiss();
			}

		});

		dialog1.show();
	}

	@Override
	public void onBackPressed()
	{
		new AlertDialog.Builder(this)
				.setMessage("Do you want to Exit")
				.setTitle("Closing Application")
				.setPositiveButton(android.R.string.cancel,
						new android.content.DialogInterface.OnClickListener()
						{
							public void onClick(
									android.content.DialogInterface dialog,
									int whichButton)
							{

							}
						})
				.setNegativeButton(android.R.string.ok,
						new android.content.DialogInterface.OnClickListener()
						{
							public void onClick(
									android.content.DialogInterface dialog,
									int whichButton)
							{

								finish();
							}
						}).show();
	}


	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		
		case R.id.linAboutUs:
			Intent intent1 = new Intent(getApplicationContext(),
					AboutUsActivity.class);
			startActivity(intent1);
			break;
		case R.id.linArtists:
			Intent intent2 = new Intent(getApplicationContext(),
					ArtistsActivity.class);
			startActivity(intent2);
			break;
		case R.id.linYoutube:
			Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/playlist?list=PL2gifkg6dG6_YD_MhUXisEUlUlD3me50Y"));
			startActivity(intent3);
			break;
		case R.id.linContactUs:
			Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.indiaifa.org/contact.html"));
			startActivity(intent4);
			break;
		case R.id.linEvents:
			callEvents();
			break;
		case R.id.linPhotos:
			Intent intent5 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.indiaifa.org/programmes/project560/photos.html"));
			startActivity(intent5);
			break;
		case R.id.linFacebook:
			Toast.makeText(getApplicationContext(), "Coming Soon..", Toast.LENGTH_SHORT).show();
			break;
		case R.id.linTwitter:
			Toast.makeText(getApplicationContext(), "Coming Soon..", Toast.LENGTH_SHORT).show();
			break;
		case R.id.linVenues:
			startActivity(new Intent(getApplicationContext(),
					VenuesListActivity.class));
			break;
		}
	}
}
