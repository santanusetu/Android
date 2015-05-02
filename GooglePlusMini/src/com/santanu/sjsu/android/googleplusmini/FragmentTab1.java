package com.santanu.sjsu.android.googleplusmini;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.PlusClient;
import com.google.android.gms.plus.internal.PlusCommonExtras;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;


import com.google.api.services.plusDomains.PlusDomains;



import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.api.services.plusDomains.model.Person;

class FragmentTab1 extends Fragment {
	
	String tokenInF1, accountNameF1;
	Person mePerson;
	PlusDomains plusDomain;
	
	private TextView txtName, txtEmail, txtOccupation, txtCompanyName;
	private LinearLayout llProfileLayout;
	private ImageView imgProfilePic;
	
	// Profile pic image size in pixels
	private static final int PROFILE_PIC_SIZE = 500;
    
	public static String tokenUniversal;
	
	String name, email, tagline, occupation, companyName;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
    
        View rootView = inflater.inflate(R.layout.profilefragment, container, false);
        
        Bundle arguments = getActivity().getIntent().getExtras();
        tokenInF1 = arguments.getString("token");
        accountNameF1 = arguments.getString("accountName");
        
        imgProfilePic = (ImageView) rootView.findViewById(R.id.imgProfilePic);
		txtName = (TextView) rootView.findViewById(R.id.txtName);
		txtEmail = (TextView) rootView.findViewById(R.id.txtEmail);
		txtOccupation = (TextView) rootView.findViewById(R.id.txtOccupation);
		txtCompanyName = (TextView) rootView.findViewById(R.id.txtCompanyName);
		
		llProfileLayout = (LinearLayout) rootView.findViewById(R.id.llProfile);
		

        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        
        GoogleCredential credential = new GoogleCredential.Builder()
            .setTransport(httpTransport)
            .setJsonFactory(jsonFactory)
            //.setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
            //.setServiceAccountScopes(SCOPE)
            //.setServiceAccountUser(USER_EMAIL)
            //.setServiceAccountPrivateKeyFromP12File(
             //   new java.io.File(SERVICE_ACCOUNT_PKCS12_FILE_PATH))
            .build();
        
        credential.setAccessToken(tokenInF1);
        
        tokenUniversal = tokenInF1;
        
        plusDomain = new PlusDomains.Builder(httpTransport, jsonFactory, credential).build();
        
        // Get user's information
        try{
        	getProfileInformation();
        }catch(Exception e)
        {
        	e.printStackTrace();
        }
        return rootView;
    }
 
    @Override
    public void onResume() {
    	super.onResume();
    }

    @Override
    public void onPause() {
    	super.onPause();
    }
    /**
	 * Fetching user's information name, email, profile pic
	 * */
	private void getProfileInformation() 
	{
		try {
			new RetriveData().execute("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private class RetriveData extends AsyncTask<String, Void, String> 
	{
		 private ProgressDialog dialog = new ProgressDialog(getActivity());

		 @Override
			protected void onPreExecute() {
				    this.dialog.setMessage("Hi "+accountNameF1+ " let me get your data ...");
				    this.dialog.show();
				    }
		 
        @Override
        protected String doInBackground(String... params) {
            
        	try {
				mePerson = plusDomain.people().get("me").execute();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) 
        {
        	 if (dialog.isShowing()) {
		            dialog.dismiss();
		        }
        	 
        	name = mePerson.getDisplayName();
        	email = accountNameF1;
        	occupation = mePerson.getOccupation();
        	List companyNameList = mePerson.getOrganizations();
        	
        	String firstCompanyName = companyNameList.get(0).toString();
        	companyName = companyNameList.toString();
        	
        	Object[] arrOfObjects = new Object[]{companyNameList.get(0)};
        	String firstItemOrg = arrOfObjects[0].toString();
        	String orgLast = null;
        	String[] org = firstItemOrg.split(",");
        	System.out.println(" org 000 "+org[0]);
        	String[] workplace =org[0].toString().split(":");
        	int length = workplace[1].length();
        	String finalWorkplace = workplace[1].substring(1, length-1);
        	System.out.println("%%%%%  workplace "+workplace[1]);
        	System.out.println("%%%%%  finalWorkplace "+finalWorkplace);
        	
        	
        	txtName.setText(name);
        	txtEmail.setText(email);
        	txtOccupation.setText(occupation);
        	//txtCompanyName.setText(firstCompanyName);
        	txtCompanyName.setText(finalWorkplace);
        	
        	
			String personPhotoUrl = mePerson.getImage().getUrl();
			personPhotoUrl = personPhotoUrl.substring(0,
					personPhotoUrl.length() - 2)
					+ PROFILE_PIC_SIZE;
			
			new LoadProfileImage(imgProfilePic).execute(personPhotoUrl);
			
        	
	       /* System.out.println("@@@ Display Name:\t" + mePerson.getDisplayName());
	        System.out.println("@@@ Profile URL:\t" + mePerson.getNickname());
	       // System.out.println("@@@ Image URL:\t" + mePerson.getImage().getUrl());
	       // System.out.println("@@@ Profile URL:\t" + mePerson.getUrl());
	        
	        System.out.println("@@@ getAboutMe:\t" + mePerson.getAboutMe());
	        System.out.println("@@@ getBirthday:\t" + mePerson.getBirthday());
	        System.out.println("@@@ getCurrentLocation:\t" + mePerson.getCurrentLocation());
	        System.out.println("@@@ getDomain:\t" + mePerson.getDomain());
	        
	        System.out.println("@@@ getOccupation:\t" + mePerson.getOccupation());
	        System.out.println("@@@ getSkills:\t" + mePerson.getSkills());
	        System.out.println("@@@ getOrganizations:\t" + mePerson.getOrganizations().toString());*/
	        
        }

    } // end of getData Asynctask
    
	/**
	 * Background Async task to load user profile picture from url
	 * */
	private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;
		//possible bug here
		//private ProgressDialog dialog = new ProgressDialog(getActivity());
		private ProgressDialog dialog = new ProgressDialog(getActivity());
		 @Override
			protected void onPreExecute() {
				    this.dialog.setMessage("Ohh I see you have a profile pic too...");
				    this.dialog.show();
				    }

		public LoadProfileImage(ImageView bmImage) {
			this.bmImage = bmImage;
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
