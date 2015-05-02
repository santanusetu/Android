package com.santanu.sjsu.android.googleplusmini;

import java.io.InputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


// santanu trying custom imageview
public class Customlistadapter extends ArrayAdapter<String>
{
	 String[] color_names;
	 String[] image_id;
	 Context context;
	 ImageView imageView;
	 int position_row;
	 
	 public Customlistadapter(Activity context,String[] image_id, String[] text)
	 {
	 super(context, R.layout.list_row_circle_profile, text);
	 // TODO Auto-generated constructor stub
	 this.color_names = text;
	 this.image_id = image_id;
	 this.context = context;
	 }
	 
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent)
	 {
	 // TODO Auto-generated method stub
	 LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 View single_row = inflater.inflate(R.layout.list_row_circle_profile, null,true);
	 
	 TextView textView = (TextView) single_row.findViewById(R.id.textView);
	 imageView = (ImageView) single_row.findViewById(R.id.imageView);
	 textView.setText(color_names[position]);
	 
	 position_row = position;
	 new LoadProfileImage(imageView).execute(image_id[position]);
	 
	 return single_row; 
	 }
	 
	 /**
		 * Background Async task to load user profile picture from url
		 * */
		private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> 
		{
			ImageView bmImage;
			//possible bug here
			//private ProgressDialog dialog = new ProgressDialog(getActivity());
			
			//private ProgressDialog dialog = new ProgressDialog(getActivity());
			/* @Override
				protected void onPreExecute() {
					    this.dialog.setMessage("Ohh I see you have a profile pic too...");
					    this.dialog.show();
					    }*/

			public LoadProfileImage(ImageView bmImage) {
				this.bmImage = bmImage;
			}

			protected Bitmap doInBackground(String... urls) {
				String urldisplay = urls[0];
				Bitmap mIcon11 = null;
				try {
					InputStream in = new java.net.URL(urldisplay).openStream();
					mIcon11 = BitmapFactory.decodeStream(in);
				} catch (Exception e) {
					Log.e("Error", e.getMessage());
					e.printStackTrace();
				}
				return mIcon11;
			}

			protected void onPostExecute(Bitmap result) 
			{
				bmImage.setImageBitmap(result);
				
			}
		} // end of loadImage Asynctask
}
