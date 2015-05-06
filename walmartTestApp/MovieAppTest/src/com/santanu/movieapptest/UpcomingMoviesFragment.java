package com.santanu.movieapptest;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.santanu.beans.MovieResponseBeans;
import com.santanu.inter.MovieDetailListener;
import com.santanu.rest.client.RestClient;
import com.santanu.services.Constant;

public class UpcomingMoviesFragment extends Fragment 
{
	private static final String TAG = "UpcomingMoviesFragment";
	
	private ListView lvUpcomingMovies;
	String castName = "";
	
	MovieDetailListener callBack;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		callBack=(MovieDetailListener) activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		getActivity().getActionBar().setTitle(MainActivity.UPCOMING_MOVIES_TITLE);
		
		View view = inflater.inflate(R.layout.fragment_in_theaters, null);
		//tvFragmentName = (TextView) view.findViewById(R.id.tvFragmentNameTheaters);
		lvUpcomingMovies = (ListView) view.findViewById(R.id.list_intheater_movies);
		return view;
	}
	
	@Override
	public void onStart() 
	{
		super.onStart();
		// calling the RestClient
		RestClient.get().getUpcomingMoviesContent(Constant.APIKEY,new Callback<MovieResponseBeans>(){
			@Override
			public void failure(RetrofitError arg0) {
				 Log.i(TAG,"Error !! Response from server :"+arg0);
				
			}

			@Override
			public void success(final MovieResponseBeans arg0, Response arg1)
			{
				 Log.i(TAG,"@@@@ Response Total No os Movies : "+arg0.getTotal());
				 Log.i(TAG,"@@@@ Response get Title of the Movie : "+arg0.getMovies().get(0).getTitle());
				 Log.i(TAG,"@@@@ Response get Release Date : "+arg0.getMovies().get(0).getRelease_dates().getTheater());
				 Log.i(TAG,"@@@@ Response getSelfLink : "+arg0.getLinks().getSelf());
				 

				 InTheatersAdapter adapter = new InTheatersAdapter(getActivity(),arg0);
				 lvUpcomingMovies.setAdapter(adapter);
				 
				lvUpcomingMovies.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view,int position, long id)
						{

							callBack.onMovieClick(arg0,position);
							
							Fragment newContent = new DetailsFragment();
							
							
							if (newContent != null)
								switchFragment(newContent);
						}
					}); 
			}   // success ends
			
		}); // RetroFit get call
		
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;

		if (getActivity() instanceof MainActivity) {
			MainActivity fca = (MainActivity) getActivity();
			fca.switchContent(fragment,"UpComingMoviesFragment");
		}

	}
}
