package com.santu.sjsu.worldcupcricket;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class WorldCupMainActivity extends Activity 
{

	static final String[] TEAM1 = new String[] { "New Zealand", "Australia",
			"South Africa", "India", "Ireland", "New Zealand", "Afghanistan",
			"UAE", "New Zealand", "Pakistan", "Australia",
			"Afghanistan", "India", "England", "West Indies", "Ireland",
			"Afghanistan", "Bangladesh", "South Africa", "New Zealand",
			"India", "England", "Pakistan", "Ireland", "Pakistan", "Australia",
			"Bangladesh", "India", "Pakistan", "Ireland", "New Zealand",
			"Australia", "England", "India", "Scotland", "South Africa",
			"New Zealand", "Afghanistan", "India", "Australia", "West Indies",
			"Ireland", "TBD", "TBD", "TBD", "TBD", "TBD", "TBD", "TBD" };

	static final String[] TEAM2 = new String[] { "Sri Lanka", "England",
			"Zimbabwe", "Pakistan", "West Indies", "Scotland", "Bangladesh",
			"Zimbabwe", "England", "West Indies", "Bangladesh", "Sri Lanka",
			"South Africa", "Scotland", "Zimbabwe", "UAE",
			"Scotland", "Sri Lanka", "West Indies", "Australia",
			"UAE", "Sri Lanka", "Zimbabwe", "South Africa",
			"UAE", "Afghanistan", "Scotland", "West Indies",
			"South Africa", "Zimbabwe", "Afghanistan", "Sri Lanka",
			"Bangladesh", "Ireland", "Sri Lanka", "UAE",
			"Bangladesh", "England", "Zimbabwe", "Scotland",
			"UAE", "Pakistan", "TBD", "TBD", "TBD", "TBD",
			"TBD", "TBD", "TBD" };

	static final String[] DATES = new String[] { "Feb-14-Sat", "Feb-14-Sat",
			"Feb-15-Sun", "Feb-15-Sun", "Feb-16-Mon", "Feb-17-Tue",
			"Feb-18-Wed", "Feb-19-Thu", "Feb-20-Fri", "Feb-21-Sat",
			"Feb-21-Sat", "Feb-22-Sun", "Feb-22-Sun", "Feb-23-Mon",
			"Feb-24-Tue", "Feb-25-Wed", "Feb-26-Thu", "Feb-26-Thu",
			"Feb-27-Fri", "Feb-28-Sat", "Feb-28-Sat", "Mar-01-Sun",
			"Mar-01-Sun", "Mar-03-Tue", "Mar-04-Wed", "Mar-04-Wed",
			"Mar-05-Thu", "Mar-06-Fri", "Mar-07-Sat", "Mar-07-Sat",
			"Mar-08-Sun", "Mar-08-Sun", "Mar-09-Mon", "Mar-10-Tue",
			"Mar-11-Wed", "Mar-12-Thu", "Mar-13-Fri", "Mar-13-Fri",
			"Mar-14-Sat", "Mar-14-Sat", "Mar-15-Sun", "Mar-15-Sun",
			"Mar-18-Wed", "Mar-19-Thu", "Mar-20-Fri", "Mar-21-Sat",
			"Mar-24-Tue", "Mar-26-Thu", "Mar-29-Sun" };

	static final int[] TEAM1_FLAG = new int[] { R.drawable.newzealand,
			R.drawable.australia, R.drawable.southafrica, R.drawable.india,
			R.drawable.ireland, R.drawable.newzealand, R.drawable.afghanistan,
			R.drawable.unitedarabemirates, R.drawable.newzealand,
			R.drawable.pakistan, R.drawable.australia, R.drawable.afghanistan,
			R.drawable.india, R.drawable.england, R.drawable.westindies,
			R.drawable.ireland, R.drawable.afghanistan, R.drawable.bangladesh,
			R.drawable.southafrica, R.drawable.newzealand, R.drawable.india,
			R.drawable.england, R.drawable.pakistan, R.drawable.ireland,
			R.drawable.pakistan, R.drawable.australia, R.drawable.bangladesh,
			R.drawable.india, R.drawable.pakistan, R.drawable.ireland,
			R.drawable.newzealand, R.drawable.australia, R.drawable.england,
			R.drawable.india, R.drawable.scotland, R.drawable.southafrica,
			R.drawable.newzealand, R.drawable.afghanistan, R.drawable.india,
			R.drawable.australia, R.drawable.westindies, R.drawable.ireland,
			R.drawable.cricket_icon_new, R.drawable.cricket_icon_new,
			R.drawable.cricket_icon_new, R.drawable.cricket_icon_new,
			R.drawable.cricket_icon_new, R.drawable.cricket_icon_new,
			R.drawable.cricket_icon_new };

	static final int[] TEAM2_FLAG = new int[] { R.drawable.srilanka,
			R.drawable.england, R.drawable.zimbabwe, R.drawable.pakistan,
			R.drawable.westindies, R.drawable.scotland, R.drawable.bangladesh,
			R.drawable.zimbabwe, R.drawable.england, R.drawable.westindies,
			R.drawable.bangladesh, R.drawable.srilanka, R.drawable.southafrica,
			R.drawable.scotland, R.drawable.zimbabwe,
			R.drawable.unitedarabemirates, R.drawable.scotland,
			R.drawable.srilanka, R.drawable.westindies, R.drawable.australia,
			R.drawable.unitedarabemirates, R.drawable.srilanka,
			R.drawable.zimbabwe, R.drawable.southafrica,
			R.drawable.unitedarabemirates, R.drawable.afghanistan,
			R.drawable.scotland, R.drawable.westindies, R.drawable.southafrica,
			R.drawable.zimbabwe, R.drawable.afghanistan, R.drawable.srilanka,
			R.drawable.bangladesh, R.drawable.ireland, R.drawable.srilanka,
			R.drawable.unitedarabemirates, R.drawable.bangladesh,
			R.drawable.england, R.drawable.zimbabwe, R.drawable.scotland,
			R.drawable.unitedarabemirates, R.drawable.pakistan,
			R.drawable.ballnew, R.drawable.ballnew, R.drawable.ballnew,
			R.drawable.ballnew, R.drawable.ballnew, R.drawable.ballnew,
			R.drawable.ballnew };

	static final String[] CITY_NAME = new String[] { "Christchurch",
			"Melbourne", "Hamilton", "Adelaide", "Nelson", "Dunedin",
			"Canberra", "Nelson", "Wellington", "Christchurch", "Brisbane",
			"Dunedin", "Melbourne", "Christchurch", "Canberra", "Brisbane",
			"Dunedin", "Melbourne", "Sydney", "Auckland", "Perth",
			"Wellington", "Brisbane", "Canberra", "Napier", "Perth", "Nelson",
			"Perth", "Auckland", "Hobart", "Napier", "Sydney", "Adelaide",
			"Hamilton", "Hobart", "Wellington", "Hamilton", "Sydney",
			"Auckland", "Hobart", "Napier", "Adelaide", "Sydney", "Melbourne",
			"Adelaide", "Wellington", "Auckland", "Sydney", "Melbourne" };

	static final String[] GROUND_NAME = new String[] { "Hagley Oval",
			"Melbourne Cricket Ground", "Seddon Park", "Adelaide Oval",
			"Saxton Oval", "University Oval", "Manuka Oval", "Saxton Oval",
			"Westpac Stadium", "Hagley Oval", "The Gabba", "University Oval",
			"Melbourne Cricket Ground	", "Hagley Oval", "Manuka Oval",
			"The Gabba", "University Oval", "Melbourne Cricket Ground",
			"Sydney Cricket Ground", "Eden Park", "W.A.C.A. Ground",
			"Westpac Stadium", "The Gabba", "Manuka Oval", "McLean Park",
			"W.A.C.A. Ground", "Saxton Oval", "W.A.C.A. Ground", "Eden Park",
			"Bellerive Oval", "McLean Park", "Sydney Cricket Ground",
			"Adelaide Oval", "Seddon Park", "Bellerive Oval",
			"Westpac Stadium", "Seddon Park", "Sydney Cricket Ground",
			"Eden Park", "Bellerive Oval", "McLean Park", "Adelaide Oval",
			"Sydney Cricket Ground", "Melbourne Cricket Ground",
			"Adelaide Oval", "Westpac Stadium", "Eden Park",
			"Sydney Cricket Ground", "Melbourne Cricket Ground" };

	static final String[] IST = new String[] { " 03:30 IST\n 11:00 local\n 14:00 PST -1d\n", 
											   " 09:00 IST\n 14:30 local\n 19:30 PST -1d\n", 
											   " 06:30 IST\n 14:00 local\n 17:00 PST -1d\n",
											   " 09:00 IST\n 14:00 local\n 19:30 PST -1d\n", 
											   " 03:30 IST\n 11:00 local\n 14:00 PST -1d\n", 
											   
											   " 03:30 IST\n 11:00 local\n 14:00 PST -1d\n",
											   " 09:00 IST\n 14:30 local\n 19:30 PST -1d\n", 
											   " 03:30 IST\n 11:00 local\n 14:00 PST -1d\n",
											   " 06:30 IST\n 14:00 local\n 17:00 PST -1d\n",
											   " 03:30 IST\n 11:00 local\n 14:00 PST -1d\n",

											   " 09:00 IST\n 13:30 local\n 19:30 PST -1d\n",
											   " 03:30 IST\n 11:00 local\n 14:00 PST -1d\n",
											   " 09:00 IST\n 14:30 local\n 19:30 PST -1d\n",
											   " 09:00 IST\n 14:30 local\n 19:30 PST -1d\n",
											   " 09:00 IST\n 14:30 local\n 19:30 PST -1d\n",
											   
											   " 09:00 IST\n 13:30 local\n 19:30 PST -1d\n",
											   " 03:30 IST\n 11:00 local\n 14:00 PST -1d\n",
											   " 09:00 IST\n 14:30 local\n 19:30 PST -1d\n",
											   " 09:00 IST\n 14:30 local\n 19:30 PST -1d\n",
											   " 06:30 IST\n 14:00 local\n 17:00 PST -1d\n",
											   
											   " 12:00 IST\n 14:30 local\n 22:30 PST -1d\n",
											   " 03:30 IST\n 11:00 local\n 14:00 PST -1d\n",
											   " 09:00 IST\n 13:30 local\n 19:30 PST -1d\n",
											   " 09:00 IST\n 14:30 local\n 19:30 PST -1d\n",
											   " 06:30 IST\n 14:00 local\n 17:00 PST -1d\n",
											   
											   " 12:00 IST\n 14:30 local\n 22:30 PST -1d\n",
											   " 03:30 IST\n 11:00 local\n 14:00 PST -1d\n",
											   " 12:00 IST\n 14:30 local\n 22:30 PST -1d\n",
											   " 06:30 IST\n 14:00 local\n 17:00 PST -1d\n",
											   " 09:00 IST\n 14:30 local\n 19:30 PST -1d\n",
											   
											   " 03:30 IST\n 11:00 local\n 14:00 PST -1d\n",
											   " 09:00 IST\n 14:30 local\n 19:30 PST -1d\n",
											   " 09:00 IST\n 14:00 local\n 20:30 PST -1d\n",
											   " 06:30 IST\n 14:00 local\n 18:00 PST -1d\n",
											   " 09:00 IST\n 14:30 local\n 20:30 PST -1d\n",
											   // 35 end
											   " 06:30 IST\n 14:00 local\n 18:00 PST -1d\n",
											   " 06:30 IST\n 14:00 local\n 18:00 PST -1d\n",
											   " 09:00 IST\n 14:30 local\n 20:30 PST -1d\n",
											   " 06:30 IST\n 14:00 local\n 18:00 PST -1d\n",
											   " 09:00 IST\n 14:30 local\n 20:30 PST -1d\n",
											   
											   " 03:30 IST\n 11:00 local\n 15:00 PST -1d\n",
											   " 09:00 IST\n 14:00 local\n 20:30 PST -1d\n",
											   
											   " 09:00 IST\n 14:30 local\n 20:30 PST -1d\n",
											   " 09:00 IST\n 14:30 local\n 20:30 PST -1d\n",
											   " 09:00 IST\n 14:00 local\n 20:30 PST -1d\n",
											   " 06:30 IST\n 14:00 local\n 18:00 PST -1d\n",
											  
											   " 06:30 IST\n 14:00 local\n 18:00 PST -1d\n",
											   " 09:00 IST\n 14:30 local\n 20:30 PST -1d\n",
											   
											   " 09:00 IST\n 14:30 local\n 20:30 PST -1d\n",
						};

	static final String[] FINALS = new String[] {
			"1st Quarter-Final\n(A1 v B4)", "2nd Quarter-Final\n(A2 v B3)",
			"3rd Quarter-Final\n(A3 v B2)", "4th Quarter-Final\n(A4 v B1)",
			"1st Semi-Final", "2nd Semi-Final", "Final" };

	//private boolean doubleBackToExitPressedOnce = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_world_cup_main);

		TextView title = (TextView) findViewById(R.id.textView1);

		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/lobster.otf");
		title.setTypeface(type);

		MyArrayAdapter adapter = new MyArrayAdapter(this, TEAM1, TEAM2, DATES,
				TEAM1_FLAG, TEAM2_FLAG, CITY_NAME, GROUND_NAME, FINALS);
		ListView list = (ListView) findViewById(R.id.listView1);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() 
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Intent intent = new Intent(getApplicationContext(),CricketDetailsActivity.class);
				intent.putExtra("TEAM1_FLAG", position);
				intent.putExtra("TEAM2_FLAG", position);
				intent.putExtra("TEAM1", TEAM1[position]);
				intent.putExtra("TEAM2", TEAM2[position]);
				intent.putExtra("CITY_NAME", CITY_NAME[position]);
				intent.putExtra("GROUND_NAME", GROUND_NAME[position]);
				intent.putExtra("DATES", position);
				intent.putExtra("IST", IST[position]);
				startActivity(intent);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.world_cup_main, menu);
		return true;
	}

	
}
