package com.santanu.movieapptest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Setting up the slideBar contents and icons
 */
public class SampleListFragment extends ListFragment 
{
	//Items to be populated in the slide bar
	public String[] menus = { "In Theaters", "Upcoming Movies"};
	
	//Icons for the Items
	int[] myImageList = new int[] { R.drawable.in_theatre,R.drawable.upcoming_movies};
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		SampleAdapter adapter = new SampleAdapter(getActivity());

		for (int i = 0; i < menus.length; i++) 
		{
			adapter.add(new SampleItem(menus[i], myImageList[i]));
		}
		setListAdapter(adapter);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		Fragment newContent = null;
		
		switch (position) 
		{
		case 0:
			newContent = new InTheaterFragment();
			break;
		case 1:
			newContent = new UpcomingMoviesFragment();
			break;
		
		}
		if (newContent != null)
			switchFragment(newContent);
	}

	
	private class SampleItem
	{
		public String tag;
		public int iconRes;

		public SampleItem(String tag, int iconRes) {
			this.tag = tag;
			this.iconRes = iconRes;
		}
	}

	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;

		if (getActivity() instanceof MainActivity) {
			MainActivity fca = (MainActivity) getActivity();
			fca.switchContent(fragment);
		}

	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}
		
		public View getView(final int position, View view, ViewGroup parent) {
			if (view == null) {
				view = LayoutInflater.from(getContext()).inflate(R.layout.row,null);
			}
			ImageView icon = (ImageView) view.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			final TextView title = (TextView) view.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);

			return view;
		}
	}
}

