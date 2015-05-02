package com.indianart;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class VenuesListActivity extends ListActivity {

	private final ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	private ListView lv;
	private SimpleAdapter adapter;
	ArrayList<String> vanueList = new ArrayList<String>();
	ArrayList<Double> latList = new ArrayList<Double>();
	ArrayList<Double> lngList = new ArrayList<Double>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.venue_list_screen);

		vanueList.add("Swami Vivekananda Metro Station");
		latList.add(12.98611111111111);
		lngList.add(77.64505555555556);
		
		vanueList.add("Suchitra Cinema and Cultural Academy, Banashankari");
		latList.add(12.925749999999999);
		lngList.add(77.56949999999999);
		
		
		vanueList.add("Vinayaka Mantapa");
		latList.add(13.06013888888889);
		lngList.add(77.59325);
		vanueList.add("Basavangudi");
		latList.add(12.947944444444445);
		lngList.add(77.56786111111111);
		vanueList.add("Nayandahalli Junction");
		latList.add(12.945444444444444);
		lngList.add(77.52808333333333);
		vanueList.add("Basavanagudi and Hanumantnagar");
		latList.add(12.947944444444445);
		lngList.add(77.56786111111111);
		
		
		
		for (int jk = 0; jk < vanueList.size(); jk++) {
			HashMap<String, Object> temp = new HashMap<String, Object>();
			temp.put("venue", vanueList.get(jk).toString());
			temp.put("lat", latList.get(jk).toString());
			temp.put("lng", lngList.get(jk).toString());

			list.add(temp);

			list1();
		}
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String selVenue = vanueList.get(arg2);
				double lat = latList.get(arg2);
				double lng = lngList.get(arg2);
				
				Bundle bundle = new Bundle();
				bundle.putDouble("LAT", lat);
				bundle.putDouble("LONGITUDE",lng);

				Intent intent = new Intent(getApplicationContext(), MapActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
				
				System.out.println("1111111111==="+selVenue);
				System.out.println("22222222==="+lat);
				System.out.println("33333333==="+lng);
				
			}
		});

	}

	private void list1() {
		adapter = new SimpleAdapter(this, list, R.layout.venue_adapter,
				new String[] { "venue" }, new int[] { R.id.tvArea });
		setListAdapter(adapter);

		lv = getListView();

	}

}