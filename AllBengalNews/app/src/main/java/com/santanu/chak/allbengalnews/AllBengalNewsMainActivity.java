package com.santanu.chak.allbengalnews;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class AllBengalNewsMainActivity extends AppCompatActivity
{
	GridView gridView;
	ArrayList<Item> gridArray = new ArrayList<Item>();
	 CustomGridViewAdapter customGridAdapter;
	 String url=null;
	 private AdView mAdView;
	 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_all_bengal_news_main);
		
		try{

		//admob ads
		mAdView = (AdView) findViewById(R.id.adView);

		AdRequest adRequest = new AdRequest.Builder().build();
	   /*AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				// Check the LogCat to get your test device ID
				.addTestDevice("5CED66B72B7A26A16FAD93785257BFAB")
				.build();*/

		mAdView.loadAd(adRequest);

		//set grid view item
		Bitmap anandabazar = BitmapFactory.decodeResource(this.getResources(), R.drawable.abpone);
		Bitmap abpananda = BitmapFactory.decodeResource(this.getResources(), R.drawable.abpananda);
		Bitmap ebela = BitmapFactory.decodeResource(this.getResources(), R.drawable.ebela_logo);
		Bitmap eisamay = BitmapFactory.decodeResource(this.getResources(), R.drawable.eisamay_logo);
		Bitmap ghanta24 = BitmapFactory.decodeResource(this.getResources(), R.drawable.ghantatwentyfour);
		Bitmap ganadabi = BitmapFactory.decodeResource(this.getResources(), R.drawable.ganadabi_logo);
		Bitmap ganasakti = BitmapFactory.decodeResource(this.getResources(), R.drawable.ganashakti_logo);
		Bitmap jagobangla = BitmapFactory.decodeResource(this.getResources(), R.drawable.jago_bangla_logo);
		Bitmap sangbadpratidin = BitmapFactory.decodeResource(this.getResources(), R.drawable.pratidin_logo);
		Bitmap statesman = BitmapFactory.decodeResource(this.getResources(), R.drawable.statesman_logo);
		Bitmap suprovat = BitmapFactory.decodeResource(this.getResources(), R.drawable.suprovatone);
		Bitmap telegraph = BitmapFactory.decodeResource(this.getResources(), R.drawable.telegraph_logo_new);
		Bitmap ubsambad = BitmapFactory.decodeResource(this.getResources(), R.drawable.uttarbanga_sambad);
		
		
		
		gridArray.add(new Item(anandabazar,"Anandabazar Patrika"));
		gridArray.add(new Item(abpananda,"ABP Ananda"));
		gridArray.add(new Item(ebela,"Ebela"));
		gridArray.add(new Item(eisamay,"Ei Samay"));
		gridArray.add(new Item(ghanta24,"24 Ghanta"));
		gridArray.add(new Item(ganadabi,"Ganadabi"));
		gridArray.add(new Item(ganasakti,"Ganashakti"));
		gridArray.add(new Item(jagobangla,"Jago Bangla"));
		gridArray.add(new Item(sangbadpratidin,"Sanbad Pratidin"));
		gridArray.add(new Item(statesman,"The StatesMan"));
		gridArray.add(new Item(suprovat,"Suprovat"));
		gridArray.add(new Item(telegraph,"The Telegraph"));
		gridArray.add(new Item(ubsambad, "UttarBanga Sambad"));
		
		
		
		gridView = (GridView) findViewById(R.id.gvGridView1);
		customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, gridArray);
		gridView.setAdapter(customGridAdapter);
		
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	try
		{
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{		    
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,long id)
			{
				//Toast.makeText(AllBengalNewsMainActivity.this,"----> "+ position + "  #Selected",Toast.LENGTH_SHORT).show();
				
				switch (position) 
				{
				case 0:
					url = "http://anandabazar.com/";
			     	openWebsite(url);
					break;
				case 1:
					url = "http://abpananda.abplive.in/";
			     	openWebsite(url);
					break;
				case 2:
					url = "http://www.ebela.in/";
			     	openWebsite(url);
					break;
				case 3:
					url = "http://eisamay.indiatimes.com/";
			     	openWebsite(url);
					break;
				case 4:
					url = "http://zeenews.india.com/bengali";
			     	openWebsite(url);
					break;
				case 5:
					url = "http://www.ganadabi.in/";
			     	openWebsite(url);
					break;
				case 6:
					url = "http://ganashakti.com/bengali/";
			     	openWebsite(url);
					break;
				case 7:
					url = "http://aitcofficial.org/jago-bangla/";
			     	openWebsite(url);
					break;
				case 8:
					url = "http://www.sangbadpratidin.in/";
			     	openWebsite(url);
					break;
				case 9:
					url = "http://www.thestatesman.com/";
			     	openWebsite(url);
					break;
				case 10:
					url = "http://www.suprovat.com/";
			     	openWebsite(url);
					break;
				case 11:
					url = "http://www.telegraphindia.com/";
			     	openWebsite(url);
					break;
				case 12:
					url = "http://www.uttarbangasambad.com/";
			     	openWebsite(url);
					break;
				}
			}
		});
	}catch(Exception e)
	{
		e.printStackTrace();
	}

}
	

	private void openWebsite(String url) 
	{
		Uri uri = Uri.parse(url);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}


	@Override
	public void onPause() {
		if (mAdView != null) {
			mAdView.pause();
		}
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mAdView != null) {
			mAdView.resume();
		}
	}

	@Override
	public void onDestroy() {
		if (mAdView != null) {
			mAdView.destroy();
		}
		super.onDestroy();
	}
}
