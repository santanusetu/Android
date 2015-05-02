package com.santanu.sjsu.android.googleplusmini;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.Json;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.plusDomains.PlusDomains;

import com.google.api.services.plusDomains.model.Circle;
import com.google.api.services.plusDomains.model.CircleFeed;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
 
public class FragmentTab2 extends Fragment {
	
	 private static final String TAG = "Fragment Tab2";
	 
	PlusDomains plusDomain;
	List<Circle> circles;
	CircleFeed circleFeed;
	PlusDomains.Circles.List listCircles;
	
	ListView listViewCircle;
	String[] circleFinal;
	SharedPreferences sharedpreferences;
	public static final String MyPREFERENCES = "MyPrefs" ;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.circlefragment, container, false);
        
        listViewCircle = (ListView)rootView.findViewById(R.id.lvCircles);
        
         sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        
         Log.i(TAG, " FragmentTab1.tokenUniversal "+FragmentTab1.tokenUniversal);

        String tokenInF2Circle = FragmentTab1.tokenUniversal;
        
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        
        GoogleCredential credential = new GoogleCredential.Builder()
            .setTransport(httpTransport)
            .setJsonFactory(jsonFactory)
            .build();
        
        credential.setAccessToken(tokenInF2Circle);
        
        plusDomain = new PlusDomains.Builder(httpTransport, jsonFactory, credential).build();
        
		try 
		{
			getCircleInformation();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return rootView;
    }


    private void getCircleInformation()
    {
    	try {
			new RetriveCircleInformation().execute("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
	}
    
    private class RetriveCircleInformation extends AsyncTask<String, Void, String> {

    	 private ProgressDialog dialog = new ProgressDialog(getActivity());
    	 

		 @Override
			protected void onPreExecute() {
				    this.dialog.setMessage("Please wait getting info about your circles...");
				    this.dialog.show();
				    }
    	
        @Override
        protected String doInBackground(String... params) {
            
        	try {

        		Log.i(TAG, "@@@   getActivity()getActivity()getActivity() "+getActivity());
        		listCircles=plusDomain.circles().list("me");
                
                circleFeed=listCircles.execute();
                Log.i(TAG, " Circle feed: "+circleFeed);
              
                circles =circleFeed.getItems();
                Log.i(TAG, " Circles : "+circles);
                
                int i = 1;
                String[] circleElements = new String[50];
                while (circles != null) {
                    for (Circle circle : circles)
                    {
                    	Log.i(TAG, " Circle name : "+circle.getDisplayName()+" Circle id : "+circle.getId());
                        circleElements[i] = circle.getDisplayName();
                        i++;
                        
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(circle.getDisplayName(), circle.getId());						
                        editor.commit();
                    }

                    int circleSize = 0;
                    for (int j = 0; j < circleElements.length; j++) {
                    	if (circleElements[j] != null) {
                    		System.out.println("@@@@@@###### "+circleElements[j]);
                    		circleSize++;
						}
					}
                    
                    circleFinal = Arrays.copyOfRange(circleElements, 1, circleSize+1);
                    for (int j = 0; j < circleFinal.length; j++)
                    {
                    		Log.i(TAG, "  circleFinal "+circleFinal[j]);
                    		//circleSize++;
					}
               
                    
                    // When the next page token is null, there are no additional pages of
                    // results. If this is the case, break.
                    if (circleFeed.getNextPageToken() != null) 
                    {
                      // Prepare the next page of results
                      listCircles.setPageToken(circleFeed.getNextPageToken());

                      // Execute and process the next page request
        				circleFeed = listCircles.execute();
                      circles = circleFeed.getItems();

                    } else {
                      circles = null;
                    }
                  } 
                
				
			} catch (Exception e) {
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
        	 
        	  ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                      getActivity().getApplicationContext(),
                      R.layout.listview_row,R.id.tvRowTextView, 
                      circleFinal);
              listViewCircle.setAdapter(adapter); 
              
              listViewCircle.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
                {
                	TextView txtview = (TextView)view.findViewById(R.id.tvRowTextView);
                    String title = txtview.getText().toString();
                     
       
                    String circleId = sharedpreferences.getString(title, ""); 
                    Log.i(TAG, "@@@@@@@@@@ circleId "+circleId);
                    // Launching new Activity on selecting single List Item
                    Intent i = new Intent(getActivity().getApplicationContext(), CircleInfoDisplay.class);
                    // sending data to new activity
                    i.putExtra("title", title);
                    i.putExtra("circleId", circleId);
                    startActivity(i);
                }
              });
        }

    } // end of getCircleInformation Asynctask
 
}
