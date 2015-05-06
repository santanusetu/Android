package com.santanu.rest.client;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

import com.santanu.services.ApiServices;
import com.squareup.okhttp.OkHttpClient;

/**
 * 
 * Singleton REST client for our rotten tomato api 
 *
 */

public class RestClient {

	//http://api.rottentomatoes.com/api/public/v1.0/lists/movies/upcoming.json?apikey=mrfz362ume2tmxb7ugbm6p22&page_limit=1
	//http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=mrfz362ume2tmxb7ugbm6p22
	 private static ApiServices REST_CLIENT;
	  private static String ROOT =  "http://api.rottentomatoes.com/api/public/v1.0/lists/movies";
	  

	  static {
	    setupRestClient();
	  }

	  private RestClient() {}

	  public static ApiServices get() {
	    return REST_CLIENT;
	  }

	  //setting up the RestCliet 
	  private static void setupRestClient() {
	    RestAdapter.Builder builder = new RestAdapter.Builder()
	     .setEndpoint(ROOT)
	     .setClient(new OkClient(new OkHttpClient()));
	    // .builder.setLogLevel(RestAdapter.LogLevel.FULL);
	        
	     RestAdapter restAdapter = builder.build();
	     REST_CLIENT = restAdapter.create(ApiServices.class);
	  }

	
}