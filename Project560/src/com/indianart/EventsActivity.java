package com.indianart;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class EventsActivity extends Activity {
	private static final String TAG = "EventsActivity";

	private EventAdapter adapter;
	private ListView list;
	Button btnBack, btnBackToOrder;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);

		list = (ListView) findViewById(R.id.list);
		setEventsData();
	}

	private void setEventsData() {
		ArrayList<DataBean> customListArrayPrevEvents = new ArrayList<DataBean>();
		ArrayList<DataBean> customListArrayUpEvents = new ArrayList<DataBean>();

		try {

			// Record 7
			DataBean db6 = new DataBean();
			db6.setDate("May 10, 2014");
			db6.setName("Artist Name: Rangasiri");
			db6.setTime("06:30 PM");
			db6.setArea("Swami Vivekananda Metro Station");
			db6.setDescription("A short street theatre performance by Rangasiri, a theatre group, leading up to their performance at the Project 560 festival.");
			db6.setLat(12.98611111111111);
			db6.setLng(77.64505555555556);
			customListArrayPrevEvents.add(db6);

			// Record 6
			DataBean db5 = new DataBean();
			db5.setDate("May 10, 2014");
			db5.setName("Artist Name: Mounesh Badiger");
			db5.setTime("05:00 PM");
			db5.setArea("Suchitra Cinema and Cultural Academy, Banashankari");
			db5.setDescription("Theatre artist Mounesh Badiger is reading from the short stories of eminent Kannada writer Masti Venkatesha Iyengar.");
			db5.setLat(12.925749999999999);
			db5.setLng(77.56949999999999);
			customListArrayPrevEvents.add(db5);

			// Record 5
			DataBean db4 = new DataBean();
			db4.setDate("May 7, 2014");
			db4.setName("Artist Name: Dimple Shah");
			db4.setTime("11:30 AM");
			db4.setArea("Basavanagudi and Hanumantnagar");
			db4.setDescription("A series of six artistic interventions in prominent spots on the streets of Basavanagudi and Hanumantnagar.");
			db4.setLat(12.947944444444445);
			db4.setLng(77.56786111111111);
			customListArrayPrevEvents.add(db4);

			// Record 4
			DataBean db3 = new DataBean();
			db3.setDate("April 26, 2014");
			db3.setName("Artist Name: 080-30 Collective");
			db3.setTime("11:00 AM");
			db3.setArea("Nayandahalli Junction");
			db3.setDescription("Site-specific performances in various parts of the city including Nayandahalli Junction, K R Market and Uttrahalli, among other areas by ten artists of the 080-30 Collective.");
			db3.setLat(12.945444444444444);
			db3.setLng(77.52808333333333);
			customListArrayPrevEvents.add(db3);
			// Record 3
			DataBean db2 = new DataBean();
			db2.setDate("April 24, 2014");
			db2.setName("Artist Name: Dimple Shah");
			db2.setTime("3PM to 6PM");
			db2.setArea("Basavangudi");
			db2.setDescription("A series of six artistic interventions in prominent spots on the streets of Basavanagudi and Hanumantnagar.");
			db2.setLat(12.947944444444445);
			db2.setLng(77.56786111111111);
			customListArrayPrevEvents.add(db2);

			// Record 2
			DataBean db1 = new DataBean();
			db1.setDate("April 13, 2013");
			db1.setName("Artist Name: Jeetin Rangher");
			db1.setTime("03:00 PM");
			db1.setArea("Vinayaka Mantapa");
			db1.setDescription("Art Adda is a series of interventions in a marriage hall that was recently sliced into half during the road construction process.");
			db1.setLat(13.06013888888889);
			db1.setLng(77.59325);
			customListArrayPrevEvents.add(db1);
			// Record 1

			DataBean db = new DataBean();
			db.setDate("April 12, 2013");
			db.setName("Artist Name: Jeetin Rangher");
			db.setTime("11:00 AM");
			db.setArea("Vinayaka Mantapa");
			db.setDescription("Art Adda is a series of interventions in a marriage hall that was recently sliced into half during the road construction process.");
			db.setLat(13.06013888888889);
			db.setLng(77.59325);
			customListArrayPrevEvents.add(db);

			if (GlobalData.chkEvent == 1) {
				adapter = new EventAdapter(this, customListArrayPrevEvents);
			} else {
				adapter = new EventAdapter(this, customListArrayUpEvents);
			}

			if (!adapter.isEmpty()) {
				list.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
