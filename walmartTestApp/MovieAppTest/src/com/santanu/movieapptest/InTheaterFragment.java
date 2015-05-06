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

/**
 * Fragment for InTheater Movie list
 */

public class InTheaterFragment extends Fragment 
{
	private static final String TAG = "InTheaterFragment";
	
	private ListView lvInTheater;
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
		getActivity().getActionBar().setTitle(MainActivity.IN_THEATER_TITLE);
		
		//View view = inflater.inflate(R.layout.fragment_upcoming_movies, null);
		View view = inflater.inflate(R.layout.fragment_in_theaters, null);
		//tvFragmentName = (TextView) view.findViewById(R.id.tvFragmentNameTheaters);
		lvInTheater = (ListView) view.findViewById(R.id.list_intheater_movies);

		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onStart() {
		super.onStart();
		
		
		RestClient.get().getInTheaterContent(Constant.APIKEY,new Callback<MovieResponseBeans>(){
			@Override
			public void failure(RetrofitError arg0) {
				 Log.i("InTheaterFragment"," Error !!! Response from server : "+arg0);
				
			}

			@Override
			public void success(final MovieResponseBeans arg0, Response arg1)
			{
				 Log.i(TAG,"Response  getTotal : "+arg0.getTotal());
				 Log.i(TAG,"Response getTitle : "+arg0.getMovies().get(0).getTitle());
				 Log.i(TAG,"Response getReseaseDate"+arg0.getMovies().get(0).getRelease_dates().getTheater());
				 
					InTheatersAdapter adapter = new InTheatersAdapter(getActivity(), arg0);
				 	lvInTheater.setAdapter(adapter);
				 	
					lvInTheater.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id)
						{
							
								callBack.onMovieClick(arg0,position);
								
							Fragment newContent = new DetailsFragment();
							if (newContent != null)
								switchFragment(newContent);
						}
					}); 
			}// end of success 
			
		});
		
	}
	

	// switching the fragment and pushing it into the backstack
	private void switchFragment(Fragment fragment)
	{
		if (getActivity() == null)
			return;

		if (getActivity() instanceof MainActivity)
		{
			MainActivity fca = (MainActivity) getActivity();
			fca.switchContent(fragment,"InTheaterFragment");
		}
	}
	
	
}