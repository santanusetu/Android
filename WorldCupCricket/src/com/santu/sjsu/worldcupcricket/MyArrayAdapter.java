package com.santu.sjsu.worldcupcricket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ViewHolder")
public class MyArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] team1;
	private final String[] team2;
	private final String[] dates;
	private final String[] finals;
	private final int[] team1_flag;
	private final int[] team2_flag;

	public MyArrayAdapter(Context context, String[] team1, String[] team2,
			String[] dates, int[] team1_flag, int[] team2_flag,
			String[] cityName, String[] groundName, String[] finals) {
		super(context, R.layout.custom_match_list, team1);
		this.context = context;
		this.team1 = team1;
		this.team2 = team2;
		this.dates = dates;
		this.team1_flag = team1_flag;
		this.team2_flag = team2_flag;
		this.finals = finals;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return team1.length;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.match_list, parent, false);
		TextView tvteam1 = (TextView) rowView.findViewById(R.id.tvteam1);
		TextView tvTeam2 = (TextView) rowView.findViewById(R.id.tvteam2);
		TextView tvDates = (TextView) rowView.findViewById(R.id.textView5);
		TextView tvMatchCount = (TextView) rowView.findViewById(R.id.textView4);
		TextView tvverses = (TextView) rowView.findViewById(R.id.textView6);
		TextView tvfinals = (TextView) rowView.findViewById(R.id.tvfinals);

		Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/lobster.otf");
		tvteam1.setTypeface(type);
		tvTeam2.setTypeface(type);
		tvverses.setTypeface(type);
		tvfinals.setTypeface(type);

		Typeface type1 = Typeface.createFromAsset(context.getAssets(),"fonts/lobster.ttf");
		tvDates.setTypeface(type1);
		tvMatchCount.setTypeface(type1);

		ImageView ivteam1 = (ImageView) rowView.findViewById(R.id.imageView1);
		ImageView ivteam2 = (ImageView) rowView.findViewById(R.id.imageView2);

		tvteam1.setText((team1[position]));
		tvTeam2.setText((team2[position]));
		tvDates.setText((dates[position]));

		tvMatchCount.setText("Match- " + (position + 1));

		// Change icon based on name
		try {
			ivteam1.setImageResource(team1_flag[position]);
			ivteam2.setImageResource(team2_flag[position]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if ((team2[position]).equalsIgnoreCase("TBD")) {
				tvfinals.setVisibility(View.VISIBLE);
				tvfinals.setText((finals[position - 42]));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowView;
	}
}