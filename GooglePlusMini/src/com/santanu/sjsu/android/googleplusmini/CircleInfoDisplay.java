package com.santanu.sjsu.android.googleplusmini;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.plusDomains.PlusDomains;
import com.google.api.services.plusDomains.model.Circle;
import com.google.api.services.plusDomains.model.CircleFeed;
import com.google.api.services.plusDomains.model.PeopleFeed;
import com.google.api.services.plusDomains.model.Person;
import com.google.api.services.plusDomains.model.Person.Image;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CircleInfoDisplay extends Activity 
{

	PlusDomains plusDomain;
	List<Circle> circles;
	CircleFeed circleFeed;
	PlusDomains.Circles.List listCircles;
	String circleId;
	PeopleFeed peopleFeed;
	String[] peopleName;
	String[] personId;

	ListView listViewCirclePeople;
	String[] peopleImageURL;
	String circleName;

	SharedPreferences sharedpreferences;
	public static final String MyPREFERENCES = "MyCirclePrefs";
	String sharedPrefSuffix, sharedPrefName;

	// Profile pic image size in pixels
	private static final int PROFILE_PIC_SIZE = 80;

	private static final String TAG = "CircleInfoDisplay";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.display_circleinfo);

		try{
		
		ActionBar mActionBarCircle = getActionBar();
		mActionBarCircle.setDisplayShowHomeEnabled(false);
		mActionBarCircle.setDisplayShowTitleEnabled(false);

		LayoutInflater mInflaterCircle = LayoutInflater.from(this);

		View mCustomView = mInflaterCircle.inflate(R.layout.actionbar_circleinfo, null);
		TextView mTitleTextViewCircle = (TextView) mCustomView.findViewById(R.id.title_text_circle);
		mTitleTextViewCircle.setText(getIntent().getStringExtra("title"));

		circleName = getIntent().getStringExtra("title");

		ImageView imageView = (ImageView) mCustomView.findViewById(R.id.ivIconBack);
		
		imageView.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View view) {
				finish();
			}
		});

		mActionBarCircle.setCustomView(mCustomView);
		mActionBarCircle.setDisplayShowCustomEnabled(true);

		if (getIntent().hasExtra("title")) {
			sharedPrefSuffix = (getIntent().getStringExtra("title"));
			sharedPrefName = MyPREFERENCES + sharedPrefSuffix;
			setTitle(getIntent().getStringExtra("title"));
			circleId = getIntent().getStringExtra("circleId");
		}

		sharedpreferences = this.getSharedPreferences(sharedPrefName,Context.MODE_PRIVATE);

		listViewCirclePeople = (ListView) findViewById(R.id.lvList);
		HttpTransport httpTransport = new NetHttpTransport();
		JsonFactory jsonFactory = new JacksonFactory();

		GoogleCredential credential = new GoogleCredential.Builder()
				.setTransport(httpTransport).setJsonFactory(jsonFactory)
				.build();

		credential.setAccessToken(FragmentTab1.tokenUniversal);
		plusDomain = new PlusDomains.Builder(httpTransport, jsonFactory,credential).build();
		getInsideCircleData();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void getInsideCircleData() {
		try {
			new RetriveInsideCircleDetailedInfo().execute("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class RetriveInsideCircleDetailedInfo extends AsyncTask<String, Void, String>
	{
		private ProgressDialog dialog = new ProgressDialog(CircleInfoDisplay.this);

		@Override
		protected void onPreExecute() {
			this.dialog.setMessage("Your circle is loading...");
			this.dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			String circleIdAsync = circleId;
			PlusDomains.People.ListByCircle listPeople;
			try {
				listPeople = plusDomain.people().listByCircle(circleIdAsync);
				listPeople.setMaxResults(100L);

				peopleFeed = listPeople.execute();

				int no = peopleFeed.getTotalItems();
				Log.i(TAG, "people feed " + peopleFeed.getItems());
				int i = 0;

				peopleName = new String[no];
				peopleImageURL = new String[no];
				
				
				if (peopleFeed.getItems() != null && peopleFeed.getItems().size() > 0)
				{

					for (Person person : peopleFeed.getItems()) 
					{
						peopleName[i] = person.getDisplayName();
						Log.i(TAG, " \t " + person.getDisplayName());
						
					
						
						String personPhotoUrl = person.getImage().getUrl();
						peopleImageURL[i] = personPhotoUrl.substring(0,personPhotoUrl.length() - 2) + PROFILE_PIC_SIZE;

						Log.i(TAG,"@@@@@ peopleImageURL "+ peopleImageURL[i]);

						// write with shared preferences
						SharedPreferences.Editor editor = sharedpreferences.edit();
						editor.putString(person.getDisplayName(),person.getId());
						editor.commit();

						i++;
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
			/*if (dialog.isShowing()) {
				dialog.dismiss();
			}*/

			Customlistadapter adapter = new Customlistadapter(CircleInfoDisplay.this, peopleImageURL, peopleName);

			Log.i(TAG,"@@@ Setting setAdapter ");
			listViewCirclePeople.setAdapter(adapter);

			if (dialog.isShowing()) {
				dialog.dismiss();
			}
			// santanu -- listening to single list item on click
			listViewCirclePeople.setOnItemClickListener(new OnItemClickListener() 
			{
						public void onItemClick(AdapterView<?> parent,View view, int position, long id) 
						{
							// selected item
							TextView txtview = (TextView) view.findViewById(R.id.textView);
							String peopleInCircleName = txtview.getText().toString();
							System.out.println("@@@ Item "+ peopleInCircleName);

							//getting people id
							String peopleInCircleId = sharedpreferences.getString(peopleInCircleName, "");
							System.out.println("@@@ peopleInCircleId "+ peopleInCircleId);

							Intent i = new Intent(getApplicationContext(),PeopleInCircleInfoDisplay.class);
							// sending data to new activity
							i.putExtra("peopleInCircleName", peopleInCircleName);
							i.putExtra("peopleInCircleId", peopleInCircleId);
							i.putExtra("circleId", circleId);
							i.putExtra("circleName", circleName);
							startActivity(i);

						}
					});

		}

	} // end of getCircleInformation Asynctask

}
