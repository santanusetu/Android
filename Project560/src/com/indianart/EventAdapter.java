package com.indianart;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EventAdapter extends BaseAdapter {

	protected static final String TAG = "EventAdapter";

	private Activity activity;
	private ArrayList<DataBean> itemList;
	private static LayoutInflater inflater = null;
	private DataBean tempValues = null;
	private String description, name, time, area, date;
	private double lat, lng;

	public EventAdapter(Activity activity, ArrayList<DataBean> itemList) {
		this.activity = activity;
		this.itemList = itemList;

		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		if (itemList.size() <= 0) {
			return 1;
		}

		return itemList.size();
	}

	public Object getItem(int position) {
		try {
			tempValues = (DataBean) itemList.get(position);

			description = tempValues.getDescription();
			date = tempValues.getDate();
			name = tempValues.getName();
			time = tempValues.getTime();
			area = tempValues.getArea();
			lat = tempValues.getLat();
			lng = tempValues.getLng();

			return position;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public Object setItem(int position) {
		tempValues = (DataBean) itemList.get(position);
		tempValues.setArea(area);
		tempValues.setDate(date);
		tempValues.setDescription(description);
		tempValues.setTime(time);
		tempValues.setName(name);
		tempValues.setLat(lat);
		tempValues.setLng(lng);

		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public static class ViewHolder {
		public TextView tvName, tvDate, tvTime, tvArea, tvDescription;
		public LinearLayout linMap;
	}

	@SuppressLint("NewApi")
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		final ViewHolder holder;
		if (convertView == null) {

			view = inflater.inflate(R.layout.events_adapter, null);

			holder = new ViewHolder();

			holder.tvName = (TextView) view.findViewById(R.id.tvName);
			holder.tvDate = (TextView) view.findViewById(R.id.tvDate);
			holder.tvTime = (TextView) view.findViewById(R.id.tvTime);
			holder.tvArea = (TextView) view.findViewById(R.id.tvArea);
			holder.tvDescription = (TextView) view
					.findViewById(R.id.tvDescription);
			holder.linMap = (LinearLayout) view.findViewById(R.id.linMap);
			holder.tvName.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
			getItem(position);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		if (itemList.size() <= 0) {
			holder.tvName.setText("No Data");

		} else {
			getItem(position);
			System.out.println("tempValues.getName()======="
					+ tempValues.getName());
			holder.tvName.setText(tempValues.getName());
			holder.tvDate.setText(tempValues.getDate());
			holder.tvTime.setText(tempValues.getTime());
			holder.tvArea.setText(tempValues.getArea());
			holder.tvDescription.setText(tempValues.getDescription());
			//view.setOnClickListener(new OnItemClickListener(position));
		}

		holder.tvName.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(activity, ArtistsActivity.class);
				activity.startActivity(intent);
			}
		});
		holder.linMap.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				bundle.putDouble("LAT", tempValues.getLat());
				bundle.putDouble("LONGITUDE", tempValues.getLng());

				Intent intent = new Intent(activity, MapActivity.class);
				intent.putExtras(bundle);
				activity.startActivity(intent);
			}
		});

		return view;
	}

	/*private class OnItemClickListener implements OnClickListener {
		private int mPosition;

		OnItemClickListener(int position) {
			mPosition = position;
		}

		public void onClick(View v) {
			// TODO Auto-generated method stub

		}

	}
*/

}
