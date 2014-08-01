package com.example.drawerlayout.tablistener;

import com.example.drawerlayout.R;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.Tab;

public class TabListener<T extends Fragment> implements android.support.v7.app.ActionBar.TabListener{

	private Fragment mFragment;
	private final Activity mactivity;
	private final Class<T> mclass;
	private final String mtag;
	
	public TabListener(Activity activity, String tag, Class<T> cla){
		
		mactivity = activity;
		mclass = cla;
		mtag = tag;
	}
	
	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if(mFragment == null){
			mFragment = Fragment.instantiate(mactivity, mclass.getName());
			ft.replace(R.id.content_frame, mFragment);
		}else{
			ft.attach(mFragment);
		}
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		if(mFragment != null){
			arg1.detach(mFragment);
		}
	}

	
}
