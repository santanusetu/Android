package com.santanu.movieapptest;

import com.android.volley.toolbox.NetworkImageView;
import com.santanu.application.ImageCacheManager;
import com.squareup.otto.Subscribe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/** 
 * Fragmnt to Display the detailed information of the movie
 */

public class DetailsFragment extends Fragment
{
	NetworkImageView image;
	TextView title;
	TextView criticsrating;
	TextView audiencescore;
	TextView releasedate;
	TextView synopsis;
	TextView castname;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_details, null);

		title=(TextView)view.findViewById(R.id.tv_details_title);
		image=(NetworkImageView)view.findViewById(R.id.iv_imageView1);
		castname = (TextView)view.findViewById(R.id.tv_details_cast);
		releasedate=(TextView)view.findViewById(R.id.tv_details_releasedate);
		criticsrating=(TextView)view.findViewById(R.id.tv_details_ratings);
		audiencescore=(TextView)view.findViewById(R.id.tv_details_audiencescore);
		synopsis=(TextView)view.findViewById(R.id.tv_details_synopsis);
		
		return view;
	}
	
	@Override
	public void onPause() 
	{
		// TODO Auto-generated method stub
		super.onPause();
		// Unregistering the bus as onPause is called
		BusProvider.getInstance().unregister(this);
	}

	@Override
	public void onResume() 
	{
		// TODO Auto-generated method stub
		super.onResume();
		// Registering in the bus to get the initial value
	    BusProvider.getInstance().register(this);
	}
	
	
	// sub part of the otto implementation  
	// notifies the data set has been changed
	
	@Subscribe public void setUIDataOnDetailsScreen(MovieDataChangedEvent event) 
	{	
		// Customizing the URL as the RottenTomato APi does not give us
		// the URL for bigger picture in their response 
		//Actually they are resizing it to a thumbnail using flixer.com
		//Their main image is stored in the cloudfront.net -- so accessing it from there
		
					String url = event.arg0.getMovies().get(event.position).getPosters().getThumbnail();
					Log.i("DetailsFragment", "@@@ URL "+url);
					String[] urlSplitted = url.split("/");
					String urlNew = "http://";
					for (int i = 5; i < urlSplitted.length; i++) {
						urlNew = urlNew+urlSplitted[i]+"/";
					}
					urlNew = urlNew.substring(0, urlNew.length()-1);
					Log.i("DetailsFragment", "@@@ URLNew "+urlNew);					
					
					image.setImageUrl(urlNew, ImageCacheManager.getInstance().getImageLoader());
					title.setText(event.arg0.getMovies().get(event.position).getTitle());
					
						String castName = "";
						int castSize = event.arg0.getMovies().get(0).getAbridged_cast().size()-1;
						int retriveNames = 0;
						if(castSize > 4)
							retriveNames = 4;
						else
							retriveNames = castSize;
					 
					 for(int size = 0; size < retriveNames; size++)
					 {
						 castName = castName + event.arg0.getMovies().get(event.position).getAbridged_cast().get(size).getName().toString() + ", ";
						 Log.i("UpcomingMoviesFragment","@@@@ getCast:::"+event.arg0.getMovies().get(event.position).getAbridged_cast().get(size).getName());
					 }
					 castName = castName.substring(0, castName.length() - 2);
					
					castname.setText(castName);
					releasedate.setText(event.arg0.getMovies().get(event.position).getRelease_dates().getTheater());
					criticsrating.setText(event.arg0.getMovies().get(event.position).getRatings().getCritics_rating());
					audiencescore.setText(event.arg0.getMovies().get(event.position).getRatings().getAudience_score() + " % ");
					synopsis.setText("\n"+event.arg0.getMovies().get(event.position).getSynopsis());
	  }
}

