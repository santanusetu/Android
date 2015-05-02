package com.indianart;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
       
		Parse.initialize(this, "kW8wlF5J0lzASStIfbDKMIUvJAy7xU3HwlnQxxcz", "GFrkyimTC3o8SUH4T27QCWGgfjojWRHH7tBvFJKb"); 
		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		ParseACL.setDefaultACL(defaultACL, true);
	}
}
