package com.santanu.movieapptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.santanu.application.ImageCacheManager;
import com.santanu.beans.MovieResponseBeans; 

/**
 * Common Adapter used for populating both InTheater & Upcoming Movie List
 */
public class InTheatersAdapter extends BaseAdapter
{
	private Context mContext;
	
	private LayoutInflater mInflater;
	MovieResponseBeans movieResponseBeans;

	public InTheatersAdapter(Context context, MovieResponseBeans movieResponseBeans)
	{
		mContext = context;
		this.movieResponseBeans = movieResponseBeans;
	}

	@Override
	public int getCount() {
		return movieResponseBeans.getMovies().size();
	}

	@Override
	public Object getItem(int position) {
		return movieResponseBeans.getMovies().get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;

		if (mInflater == null)
			mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_intheater_movies, null);

			// initializing the view holder
			viewHolder = new ViewHolder();
			viewHolder.ivImage = (NetworkImageView) convertView.findViewById(R.id.iv_image_inthr);

			viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title_inthr);
			viewHolder.tvDate = (TextView) convertView.findViewById(R.id.tv_date_inthr);
			
			convertView.setTag(viewHolder);
			
		} else {
			// recycling the already inflated view
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.tvTitle.setText(movieResponseBeans.getMovies().get(position).getTitle());
		viewHolder.tvDate.setText(movieResponseBeans.getMovies().get(position).getRelease_dates().getTheater());
		viewHolder.ivImage.setImageUrl(movieResponseBeans.getMovies().get(position).getPosters().getThumbnail(),  ImageCacheManager.getInstance().getImageLoader());
		return convertView;
	}

	
	/**
	 * The view holder design pattern prevents using findViewById() repeatedly in getView()
	 */
	private static class ViewHolder {
		NetworkImageView ivImage;
		TextView tvTitle;
		TextView tvDate;

	}
}
