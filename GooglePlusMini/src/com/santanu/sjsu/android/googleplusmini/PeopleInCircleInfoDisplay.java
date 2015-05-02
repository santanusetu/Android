package com.santanu.sjsu.android.googleplusmini;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.plusDomains.PlusDomains;
import com.google.api.services.plusDomains.model.Person;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.google.api.services.plusDomains.model.PeopleFeed;


public class PeopleInCircleInfoDisplay extends Activity
{
	String peopleInCircleId, peopleNameCircle;
	PlusDomains plusDomain;
	String circleId;
	
	String occupation = " ";
	String email = " ";
	String personCircleName;
	
	private ImageView imgProfilePicCircle;
	private TextView nameCircle, emailCircle, occupationCircle, companyNameCircle;
	TextView circleName;
	private LinearLayout llProfileLayoutCircle;
	String circlePersonPhotoUrl;
	 private static final String TAG = "PeopleInCircleInfoDisplay";
	
	// Profile pic image size in pixels
	private static final int PROFILE_PIC_SIZE = 500;
	//santanu newtry
	Person mePersonCircle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infodisplay_peropleincircle);

	try{
		
		ActionBar mActionBar = getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		//mActionBar.setIcon(R.drawable.ic_launcher);

		LayoutInflater mInflater = LayoutInflater.from(this);

		View mCustomView = mInflater.inflate(R.layout.customlayoutaction, null);
		TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
		mTitleTextView.setText(getIntent().getStringExtra("peopleInCircleName"));

		
		ImageView imageView = (ImageView) mCustomView.findViewById(R.id.ivIconBackPerson);
		imageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				//Toast.makeText(getApplicationContext(), "Back Clicked!",Toast.LENGTH_LONG).show();
				finish();
			}
		});
		
		ImageButton imageButton = (ImageButton) mCustomView.findViewById(R.id.imageButton);
		imageButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Toast.makeText(getApplicationContext(), "Email Clicked!",Toast.LENGTH_LONG).show();
				
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("plain/text");
				intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
				intent.putExtra(Intent.EXTRA_TEXT, "mail body");
				startActivity(Intent.createChooser(intent, ""));
			}
		});

		mActionBar.setCustomView(mCustomView);
		mActionBar.setDisplayShowCustomEnabled(true);
	
		
		  peopleNameCircle = (getIntent().getStringExtra("peopleInCircleName"));
          
		  // santanu use this
		  peopleInCircleId = getIntent().getStringExtra("peopleInCircleId");
        
          circleId = getIntent().getStringExtra("circleId");
          personCircleName = getIntent().getStringExtra("circleName");
          
		 imgProfilePicCircle = (ImageView)findViewById(R.id.ivProfilePicCircle);
		 nameCircle = (TextView)findViewById(R.id.tvNameCircle);
		 
		 //emailCircle= (TextView)findViewById(R.id.tvEmailCrcle);
		 
		 occupationCircle = (TextView)findViewById(R.id.tvOccupationCircle);
		 companyNameCircle = (TextView)findViewById(R.id.tvComapanyNameCircle);
		 circleName = (TextView)findViewById(R.id.tvCircleName);
		
		HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        
        GoogleCredential credential = new GoogleCredential.Builder()
            .setTransport(httpTransport)
            .setJsonFactory(jsonFactory)
            .build();
        
        credential.setAccessToken(FragmentTab1.tokenUniversal);
        
        plusDomain = new PlusDomains.Builder(httpTransport, jsonFactory, credential).build();
        
        getPeopleInCircleData();
        
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	private void getPeopleInCircleData() 
	{
    	try {
			new RetrivePeopleInCircleInfo().execute("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 private class RetrivePeopleInCircleInfo extends AsyncTask<String, Void, String> 
	 {

		 private ProgressDialog dialog = new ProgressDialog(PeopleInCircleInfoDisplay.this);
		 
		 @Override
		    protected void onPreExecute() {
		        this.dialog.setMessage("Please wait data is loading...");
		        this.dialog.show();
		    }
		 
	        @Override
	        protected String doInBackground(String... params) 
	        {

	        	PlusDomains.People.ListByCircle listPeople;
	        	
				try {
					listPeople = plusDomain.people().listByCircle(circleId);
					listPeople.setMaxResults(100L);
					
					PeopleFeed peopleFeed = listPeople.execute();
					System.out.println("Google+ users circled:");
					System.out.println(peopleFeed.getItems());

				
					if(peopleFeed.getItems() != null && peopleFeed.getItems().size() > 0 )
					{
					  for(Person person : peopleFeed.getItems()) {
					    String name = person.getDisplayName();
					   
					    if(person.getDisplayName().equalsIgnoreCase(peopleNameCircle) && person.getId().equalsIgnoreCase(peopleInCircleId))
					    {
					    	System.out.println(" @@@@@@@@@@ Got in to here "+name);
					    	System.out.println(" @@@ all the info about the person ");
					    	
					    	System.out.println("\n "+person.getAboutMe());
					    	System.out.println("\n "+person.getBirthday());
					    	System.out.println("\n "+person.getBraggingRights());
					    	System.out.println("\n "+person.getCurrentLocation());
					    	System.out.println("\n "+person.getDisplayName());
					    	System.out.println("\n "+person.getDomain());
					    	System.out.println("\n "+person.getEtag());
					    	System.out.println("\n "+person.getGender());
					    	System.out.println("\n "+person.getKind());
					    	System.out.println("\n "+person.getNickname());
					    	System.out.println("\n "+person.getObjectType());
					    	System.out.println("\n "+person.getOccupation());
					    	System.out.println("\n "+person.getRelationshipStatus());
					    	System.out.println("\n "+person.getEmails());
					    	
					    	if(person.getEmails() != null)
					    	{
					    		email = person.getEmails().toString();
					    	}else{
					    		email = "Email not specified";
					    	}
					    	
					    	if(person.getOccupation() != null)
					    	occupation = person.getOccupation();
					    	else{
					    		occupation = "Occupation not specified by this person";
					    	}

					    	circlePersonPhotoUrl = person.getImage().getUrl();
					    	circlePersonPhotoUrl = circlePersonPhotoUrl.substring(0,
					    			circlePersonPhotoUrl.length() - 2)
									+ PROFILE_PIC_SIZE;
					    	
					    }
					  }
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	return "Executed";
	        }

	        @Override
	        protected void onPostExecute(String result) 
	        {
	        	nameCircle.setText(" "+peopleNameCircle); 
	        	circleName.setText("You hava put this person on "+personCircleName+" circle");
		    	//emailCircle.setText(" "+email);
		    	occupationCircle.setText(" "+occupation);
		    	new LoadProfileImageCircle(imgProfilePicCircle).execute(circlePersonPhotoUrl);
		    	
		    	if (dialog.isShowing()) {
		            dialog.dismiss();
		        }
	        }

	    } // end of RetrivePeopleInCircleInfo Asynctask
	 
	 /**
		 * Background Async task to load user profile picture from url
		 * */
		private class LoadProfileImageCircle extends AsyncTask<String, Void, Bitmap> {
			ImageView bmImage;
			private ProgressDialog dialog = new ProgressDialog(PeopleInCircleInfoDisplay.this); 
		
			
			public LoadProfileImageCircle(ImageView bmImage) {
				this.bmImage = bmImage;
			}

			@Override
			protected void onPreExecute() {
				    this.dialog.setMessage("Please wait profile pic is on its way...");
				    this.dialog.show();
				    }
			protected Bitmap doInBackground(String... urls) {
				String urldisplay = urls[0];
				Bitmap mIcon11 = null;
				try {
					InputStream in = new java.net.URL(urldisplay).openStream();
					mIcon11 = BitmapFactory.decodeStream(in);
				} catch (Exception e) {
					Log.e("Error", e.getMessage());
					e.printStackTrace();
				}
				return mIcon11;
			}

			protected void onPostExecute(Bitmap result) {
				bmImage.setImageBitmap(result);
				
				if (dialog.isShowing()) {
		            dialog.dismiss();
		        }
			}
		} // end of loadImage Asynctask
		
	 
}
