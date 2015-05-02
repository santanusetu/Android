package com.indianart.gps;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class GPS_Position implements LocationListener {
	LocationManager locationManager;
	public Location location = null;
	Context contxt;

	public GPS_Position(Context mcontext) {
		// TODO Auto-generated constructor stub
		this.contxt = mcontext;
		getGPS_Position();

	}

	private void getGPS_Position() {
		// TODO Auto-generated method stub
		locationManager = (LocationManager) contxt
				.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		String provider = null;
		try {

			provider = locationManager.getBestProvider(criteria, true);
			if (provider != null) {
				

				locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 100, // 1min
						0, // 1km
						this);
				location = locationManager.getLastKnownLocation(provider);
			}

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("@@@@@", "errror in gps class " + e.toString());
		}

	}

	public double getLatitudeGPS() {
		if (location != null)
			return location.getLatitude();
		return 0;
	}

	public double getLongitudeGPS() {
		if (location != null)
			return location.getLongitude();
		return 0;
	}

	public double distanceFromCurrent(double lat1, double lon1, double lat2,
			double lon2) {
		double theta = lon1 - lon2;

		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;

		Location loctn1, loctn2;
		loctn1 = new Location("");
		loctn2 = new Location("");

		loctn1.setLatitude(lat1);
		loctn1.setLongitude(lon1);

		loctn2.setLatitude(lat2);
		loctn2.setLongitude(lon2);
		dist = loctn2.distanceTo(loctn1);

		Log.e("  ", "Distanceto function result in meters ::" + dist);
		Log.e("In Km ", "After calculating meter to km:::" + (dist * 0.001));
		dist = dist * 0.001;
		return (dist);

	}

	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
