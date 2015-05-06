package com.santanu.movieapptest;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.santanu.beans.MovieResponseBeans;
import com.santanu.inter.MovieDetailListener;
import com.squareup.otto.Produce;

/**
 * Launcher Activity for the Application
 */
public class MainActivity extends BaseActivity implements MovieDetailListener
{

	public static final String IN_THEATER_TITLE = "In Theaters";
	public static final String UPCOMING_MOVIES_TITLE = "Upcoming Movies";
	
	private Fragment mContent;

	MovieResponseBeans arg0;
	int position;
	  
	public MainActivity() {
		super(R.string.hello_world);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");

		//Setting up Initial InTheater Fragment
		if (mContent == null)
			mContent = new InTheaterFragment();
			//mContent = new UpcomingMoviesFragment();

		setContentView(R.layout.content_frame);
		 
		// Customizing the color of the Action Bar 
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FCA614")));
	
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mContent).commit();

		setBehindContentView(R.layout.menu_frame);
		getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new SampleListFragment()).commit();
	}

	
	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
		getSlidingMenu().showContent();
	}
	
	// Maintaining Fragment stack to handle Backpress in Nested Fragments
	// str1 is the name with which the Fragment is getting pushed in the stack
	
	public void switchContent(Fragment fragment,String str1) {
		mContent = fragment;
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(str1).commit();
		
		getSlidingMenu().showContent();
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// Unregistering when an object is no longer on the bus.
	    BusProvider.getInstance().unregister(this);
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// Registering so that we can have the initial value.
	    BusProvider.getInstance().register(this);
	}
	
	@Produce public MovieDataChangedEvent produceLocationEvent() {
	    // Produce an initial value for movies
		  Log.i("Producer", "produceLocationEvent called ");
	    return new MovieDataChangedEvent(arg0, position);
	  }

	@Override
	public void onMovieClick(MovieResponseBeans arg0,int position) {
		
		this.arg0 = arg0;
	    this.position = position;
		
		 BusProvider.getInstance().post(produceLocationEvent());
	}
	
	@Override
	public void onBackPressed() 
	{
		//Toast.makeText(MainActivity.this, "Back pressed", Toast.LENGTH_SHORT).show();
		int count = getFragmentManager().getBackStackEntryCount();

	    if (count == 0) {
	        super.onBackPressed();
	        //additional code
	    } else {
	        getFragmentManager().popBackStack();
	    }
	
	} //backbutton pressed

}
