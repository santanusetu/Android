package com.indianart.gps;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class GPSLocation implements LocationListener {
	private static final String TAG = "GPSLocation";
	double lat, lng;
	LocationManager locationManager;
	public Location location = null;
	Context contxt;
	private String provider;
	public boolean isavailable,isgps;

	public GPSLocation(Context mcontext) {
		// TODO Auto-generated constructor stub
		this.contxt = mcontext;
		// Get the location manager
		locationManager = (LocationManager) contxt
				.getSystemService(Context.LOCATION_SERVICE);
		// Define the criteria how to select the locatioin provider -> use
		// default
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		location = locationManager.getLastKnownLocation(provider);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, // 1min
				0, // 1km
				this);
		// Initialize the location fields
		/*if (location != null) {
			System.out.println("Provider " + provider + " has been selected.");
			lat = (location.getLatitude());
			lng = (location.getLongitude());
			isavailable = true;
			
		} else {
			
			isavailable = false;
		}*/
		if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
		{
			isgps=false;
		}
		else
		{
			isgps=true;
		}

	}

	public double getLatitudeGPS() {
		return location.getLatitude();
	}

	public double getLongitudeGPS() {
		return location.getLongitude();
	}

	public double distanceFromCurrent(double lat1, double lon1, double lat2,
			double lon2) {

		double dist;

		Location loctn1, loctn2;
		loctn1 = new Location("");
		loctn2 = new Location("");

		loctn1.setLatitude(lat1);
		loctn1.setLongitude(lon1);

		loctn2.setLatitude(lat2);
		loctn2.setLongitude(lon2);
		dist = loctn2.distanceTo(loctn1);
		dist = dist * 0.001;
		return (dist);

	}

	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		//Log.i(TAG, "Location"+arg0);
		if(arg0!=null)
		{
			
		lat = (arg0.getLatitude());
		lng = (arg0.getLongitude());
		isavailable = true;
		}
		else
		{
			isavailable = false;
		}
	}

	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
