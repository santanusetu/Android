package com.santu.sjsu.worldcupcricket;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CricketDetailsActivity extends Activity {

	TextView teamFirst, teamSecond, cityName, groundName, finalName,indianTime, tv_verses,day;
	ImageView flagTeam1, flagTeam2;
	String team1, team2, city, ground, ist, finals;
	int flagteam1, flagteam2, datestobestart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cricket_details);
		getCricketDetailsData();


		teamFirst = (TextView) findViewById(R.id.tvteam1);
		teamSecond = (TextView) findViewById(R.id.tvteam2);
		cityName = (TextView) findViewById(R.id.tvcity);
		groundName = (TextView) findViewById(R.id.tvground);
		day = (TextView) findViewById(R.id.tvday);
		indianTime = (TextView) findViewById(R.id.tvindiantime);
		flagTeam1 = (ImageView) findViewById(R.id.ivteam1);
		flagTeam2 = (ImageView) findViewById(R.id.ivteam2);

		tv_verses = (TextView) findViewById(R.id.tv_verses);

		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/lobster.otf");
		teamFirst.setTypeface(type);
		teamSecond.setTypeface(type);
		tv_verses.setTypeface(type);

		teamFirst.setText(team1);
		teamSecond.setText(team2);
		cityName.setText(city);
		groundName.setText(ground);
		day.setText(WorldCupMainActivity.DATES[datestobestart]);
		indianTime.setText(ist);
		flagTeam1.setImageResource(WorldCupMainActivity.TEAM1_FLAG[flagteam1]);
		flagTeam2.setImageResource(WorldCupMainActivity.TEAM2_FLAG[flagteam2]);

	}

	/*public void liveTV(View view) 
	{
		if (Util.isNetworkAvailable(CricketDetailsActivity.this)) {
			Intent intent = new Intent(getApplicationContext(),WebViewActivity.class);
			intent.putExtra("url", "http://www.crictime.com/"); // getText()
			// should not be static
			startActivity(intent);
		} else {
			Util.settingAlert(CricketDetailsActivity.this);
		}

	}
*/
	public void liveScore(View view) 
	{
		if (Util.isNetworkAvailable(CricketDetailsActivity.this))
		{
			Intent intent = new Intent(getApplicationContext(),WebViewActivity.class);
			intent.putExtra("url","http://m.espncricinfo.com/ci/engine/match/index.html"); // getText()
			// should not be static
			startActivity(intent);
		} else {
			Util.settingAlert(CricketDetailsActivity.this);
		}

	}

	/*public void onShare(View view) {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent
				.putExtra(
						Intent.EXTRA_TEXT,
						"Check out \"WorldCupFever \""
								+ " - "
								+ "https://play.google.com/store/apps/details?id=com.santu.sjsu.worldcupcricket&hl=en");
		startActivity(Intent.createChooser(shareIntent, "share With Friends"));
	}*/

	private void getCricketDetailsData() {
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			flagteam1 = extras.getInt("TEAM1_FLAG");
			flagteam2 = extras.getInt("TEAM2_FLAG");
			team1 = extras.getString("TEAM1");
			team2 = extras.getString("TEAM2");
			city = extras.getString("CITY_NAME");
			ground = extras.getString("GROUND_NAME");
			datestobestart = extras.getInt("DATES");
			ist = extras.getString("IST");

		}
	}

}
