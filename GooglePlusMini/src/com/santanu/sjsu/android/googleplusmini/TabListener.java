package com.santanu.sjsu.android.googleplusmini;

import android.app.ActionBar.Tab;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;

public class TabListener implements ActionBar.TabListener 
{
		Fragment fragment;
	 
		public TabListener(Fragment fragment)
		{
			this.fragment = fragment;
		}
	 
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) 
		{
			// TODO Auto-generated method stub
			ft.replace(R.id.fragment_container, fragment);
		}
	 
		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) 
		{
			// TODO Auto-generated method stub
			ft.remove(fragment);
		}
	 
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
	 
		}

}
