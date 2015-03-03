package com.santanu.chak.allbengalnews;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;

public class AllBengalNewsMainActivity extends Activity 
{
	GridView gridView;
	ArrayList<Item> gridArray = new ArrayList<Item>();
	 CustomGridViewAdapter customGridAdapter;
	 String url=null;
	 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_all_bengal_news_main);
		
		try{
		//set grid view item
		Bitmap aajkal = BitmapFactory.decodeResource(this.getResources(), R.drawable.aajkaal_logo);
		Bitmap anandabazar = BitmapFactory.decodeResource(this.getResources(), R.drawable.abp1);
		Bitmap abpananda = BitmapFactory.decodeResource(this.getResources(), R.drawable.abpananda);
		Bitmap dgtimes = BitmapFactory.decodeResource(this.getResources(), R.drawable.dgtimes_logo);
		Bitmap ebela = BitmapFactory.decodeResource(this.getResources(), R.drawable.ebela_logo);
		Bitmap eisamay = BitmapFactory.decodeResource(this.getResources(), R.drawable.eisamay_logo);
		Bitmap ghanta24 = BitmapFactory.decodeResource(this.getResources(), R.drawable.ghanta24);
		Bitmap ganadabi = BitmapFactory.decodeResource(this.getResources(), R.drawable.ganadabi_logo);
		Bitmap ganasakti = BitmapFactory.decodeResource(this.getResources(), R.drawable.ganashakti_logo);
		Bitmap jagobangla = BitmapFactory.decodeResource(this.getResources(), R.drawable.jago_bangla_logo);
		Bitmap sangbadpratidin = BitmapFactory.decodeResource(this.getResources(), R.drawable.pratidin_logo);
		Bitmap statesman = BitmapFactory.decodeResource(this.getResources(), R.drawable.statesman_logo);
		Bitmap suprovat = BitmapFactory.decodeResource(this.getResources(), R.drawable.suprovat1);
		Bitmap newstime = BitmapFactory.decodeResource(this.getResources(), R.drawable.newstime);
		Bitmap telegraph = BitmapFactory.decodeResource(this.getResources(), R.drawable.telegraph_logo_new);
		Bitmap ubsambad = BitmapFactory.decodeResource(this.getResources(), R.drawable.uttarbanga_sambad);
		
		
		
		gridArray.add(new Item(aajkal,"Aajkal"));
		gridArray.add(new Item(anandabazar,"Anandabazar Patrika"));
		gridArray.add(new Item(abpananda,"ABP Ananda"));
		gridArray.add(new Item(dgtimes, "Darjeeling Times"));
		gridArray.add(new Item(ebela,"Ebela"));
		gridArray.add(new Item(eisamay,"Ei Samay"));
		gridArray.add(new Item(ghanta24,"24 Ghanta"));
		gridArray.add(new Item(ganadabi,"Ganadabi"));
		gridArray.add(new Item(ganasakti,"Ganashakti"));
		gridArray.add(new Item(jagobangla,"Jago Bangla"));
		gridArray.add(new Item(sangbadpratidin,"Sanbad Pratidin"));
		gridArray.add(new Item(statesman,"The StatesMan"));
		gridArray.add(new Item(suprovat,"Suprovat"));
		gridArray.add(new Item(newstime, "News Time"));
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
					url = "http://www.aajkaal.net/";
			     	openWebsite(url);
					break;
				case 1:
					url = "http://anandabazar.com/";
			     	openWebsite(url);
					break;
				case 2:
					url = "http://abpananda.abplive.in/";
			     	openWebsite(url);
					break;
				case 3:
					url = "http://www.darjeelingtimes.com/";
			     	openWebsite(url);
					break;
				case 4:
					url = "http://www.ebela.in/";
			     	openWebsite(url);
					break;
				case 5:
					url = "http://eisamay.indiatimes.com/";
			     	openWebsite(url);
					break;
				case 6:
					url = "http://zeenews.india.com/bengali";
			     	openWebsite(url);
					break;
				case 7:
					url = "http://www.ganadabi.in/";
			     	openWebsite(url);
					break;
				case 8:
					url = "http://ganashakti.com/bengali/";
			     	openWebsite(url);
					break;
				case 9:
					url = "https://aitmc.org/jagobangla.php";
			     	openWebsite(url);
					break;
				case 10:
					url = "http://www.sangbadpratidin.in/";
			     	openWebsite(url);
					break;
				case 11:
					url = "http://www.thestatesman.net/";
			     	openWebsite(url);
					break;
				case 12:
					url = "http://www.suprovat.com/";
			     	openWebsite(url);
					break;
				case 13:
					url = "http://newstimebangla.com/";
			     	openWebsite(url);
					break;
				case 14:
					url = "http://www.telegraphindia.com/";
			     	openWebsite(url);
					break;
				case 15:
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
}
