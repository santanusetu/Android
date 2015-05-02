package com.indianart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.indianart.gps.GPSLocation;

public class MapActivity extends FragmentActivity implements LocationListener {
	GPSLocation gps;
	boolean gps_enabled = false;
	boolean network_enabled = false;
	LocationManager locationManager;
	private String provider;
	public Location location = null;
	double latt = 0, lngg = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		gps = new GPSLocation(getApplicationContext());
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		location = locationManager.getLastKnownLocation(provider);
		try {
			gps_enabled = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch (Exception ex) {
		}
		try {
			network_enabled = locationManager
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		} catch (Exception ex) {
		}

		if (gps_enabled) {
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 0, 0, this);
		}
		if (network_enabled) {
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 0, 0, this);
		}
		
		if (location != null) {
			latt = (location.getLatitude());
			lngg = (location.getLongitude());
			System.out.println("lat and long is::"+latt + "==and==" +lngg);
		} else {
			locationNotget();
		}
		
		Bundle bundle = getIntent().getExtras();
		double LAT = bundle.getDouble("LAT");
		double LONGITUDE = bundle.getDouble("LONGITUDE");
		
		
			String uri = "http://maps.google.com/maps?f=a&saddr=" + latt+","+lngg+"&daddr="+LAT+","+LONGITUDE;
			Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
			intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
			startActivity(intent);
			finish();
	}

	

	private void locationNotget() {
			
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					MapActivity.this);

			// set title
			alertDialogBuilder.setTitle("IndianArt");

			// set dialog message
			alertDialogBuilder
					.setMessage(
							"Oops! Couldn't locate you Please Restart GPS And App.")
					.setCancelable(false)
					.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							
						}
					});

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();
		
		
	}



	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
