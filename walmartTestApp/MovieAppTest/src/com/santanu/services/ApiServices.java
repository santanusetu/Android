package com.santanu.services;

import com.santanu.beans.MovieResponseBeans;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * 
 * Interface which is used in making REST calls to 
 * the Rotten Tomato API server -- Defines HTTP methods using annotations
 *
 */
public interface ApiServices {

	 ///http://api.rottentomatoes.com/api/public/v1.0/lists/movies/upcoming.json?apikey=mrfz362ume2tmxb7ugbm6p22&page_limit=1
	//
	 @GET("/upcoming.json")
	 public void getUpcomingMoviesContent(@Query("apikey") String apiKeyName, Callback<MovieResponseBeans> callback);
	  //public void getUpcomingMoviesContent(@Query("apikey") String apiKeyName, @Query("page_limit") String pageNo, Callback<MovieResponseBeans> callback);
	 
	 @GET("/in_theaters.json")
	  public void getInTheaterContent(@Query("apikey") String apiKeyName,Callback<MovieResponseBeans> callback);
	 

}
