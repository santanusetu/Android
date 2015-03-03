package com.santu.sjsu.worldcupcricket;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Util 
{
	public static boolean isNetworkAvailable(Context context) 
	{
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static void settingAlert(final Context context) 
	{
		new AlertDialog.Builder(context)
				.setTitle("Internet Alert")
				.setMessage("Do you want to open Settings?")
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int which) 
							{
								context.startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
							}
						}).setNegativeButton(android.R.string.no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int which) {
								// do nothing
							}
						}).setIcon(android.R.drawable.ic_dialog_alert).show();
	}

	/*
	 * public static void toastMsg(String msg,final Activity activity) {
	 * 
	 * LayoutInflater inflater = activity.getLayoutInflater(); View layout =
	 * inflater.inflate(R.layout.custom_toast, (ViewGroup)
	 * activity.findViewById(R.id.toast_layout)); TextView tv = ((TextView)
	 * layout.findViewById(R.id.toast_text_1)); //tv.setTypeface(type_regular);
	 * tv.setText(msg); Toast toast1 = new Toast(activity.getBaseContext());
	 * toast1.setDuration(Toast.LENGTH_LONG); toast1.setGravity(Gravity.TOP |
	 * Gravity.CENTER, 0, 200); toast1.setView(layout); toast1.show(); }
	 */

}
