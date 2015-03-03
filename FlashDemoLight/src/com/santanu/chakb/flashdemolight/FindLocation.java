package com.santanu.chakb.flashdemolight;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FindLocation extends Activity 
{
	// Google Map
	private GoogleMap googleMap;

	double latitude = 27.385044;
	double longitude = 78.486671;
	ImageView mapType;
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.findlocation);

		try {
			mapType = (ImageView)findViewById(R.id.ivMaptype);
			Toast t=Toast.makeText(getApplicationContext(),"Click on the top right icon for closest view...", Toast.LENGTH_SHORT);
			t.setGravity(Gravity.CENTER,0,0);
			t.show();
			
			// Loading map
			initilizeMap();

			// Changing map type
			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			 //googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
			
			// Showing / hiding your current location
			googleMap.setMyLocationEnabled(true);
			// Enable / Disable zooming controls
			googleMap.getUiSettings().setZoomControlsEnabled(true);
			// Enable / Disable my location button
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);
			// Enable / Disable Compass icon
			googleMap.getUiSettings().setCompassEnabled(true);
			// Enable / Disable Rotate gesture
			googleMap.getUiSettings().setRotateGesturesEnabled(true);
			// Enable / Disable zooming functionality
			googleMap.getUiSettings().setZoomGesturesEnabled(true);

			//santanu new
			 // Get LocationManager object from System Service LOCATION_SERVICE
		    LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		    // Create a criteria object to retrieve provider
		    Criteria criteria = new Criteria();
		    // Get the name of the best provider
		    String provider = locationManager.getBestProvider(criteria, true);
		    // Get Current Location
		    Location myLocation = locationManager.getLastKnownLocation(provider);

		 // Get latitude and longitude of the current location
		    double latitude1 = myLocation.getLatitude();
		    double longitude1 = myLocation.getLongitude();
		    
		    Log.i("FindLocation","@@@@@@@@@@@@ user lati :: "+latitude1+"  longi :: "+longitude1);
				// Adding a marker
				MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude1, longitude1)).title("You are here !! " );
				marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				googleMap.addMarker(marker);

				// Move the camera to last position with a zoom level
				CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude,longitude)).zoom(0).build();
			    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onResume() 
	{
		super.onResume();
		initilizeMap();
		
		mapType.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				AlertDialog.Builder builderSingle = new AlertDialog.Builder(FindLocation.this);
	            builderSingle.setIcon(R.drawable.globe);
	            builderSingle.setTitle("Select Map type ");
	            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(FindLocation.this,android.R.layout.select_dialog_singlechoice);
	            arrayAdapter.add("Normal");
	            arrayAdapter.add("Hybrid");
	            arrayAdapter.add("Satellite");
	            arrayAdapter.add("Terrain");
	       
	            builderSingle.setCancelable(true);
	            builderSingle.setAdapter(arrayAdapter,new DialogInterface.OnClickListener()
	            {
	                        @Override
	                        public void onClick(DialogInterface dialog, int which) 
	                        {
	                            String strName = arrayAdapter.getItem(which);
	                            
	                            if (strName.equalsIgnoreCase("Normal")) 
	                            {
	                            	googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
								}
	                            else if (strName.equalsIgnoreCase("Hybrid")) 
	                            {
	                            	googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
								}
	                            else if (strName.equalsIgnoreCase("Satellite")) 
	                            {
	                            	googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
								}
	                            else if (strName.equalsIgnoreCase("Terrain")) 
	                            {
	                            	googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
								}
	                        }
	                    });
	            builderSingle.show();
			}
		});
	}

	/**
	 * function to load map If map is not created it will create it for you
	 * */
	private void initilizeMap()
	{
		if (googleMap == null) 
		{
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.fMap)).getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),"Sorry!! Unable to create Maps...", Toast.LENGTH_SHORT).show();
			}
		}
	}


}

