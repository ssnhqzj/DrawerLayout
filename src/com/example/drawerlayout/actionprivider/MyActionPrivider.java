package com.example.drawerlayout.actionprivider;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;

import com.example.drawerlayout.R;

public class MyActionPrivider extends ActionProvider {

	@Override
	public boolean hasSubMenu() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onPrepareSubMenu(SubMenu subMenu) {
		//super.onPrepareSubMenu(subMenu);
		subMenu.clear();  
        subMenu.add("sub item 1").setIcon(R.drawable.ic_launcher)  
                .setOnMenuItemClickListener(new OnMenuItemClickListener() {  
                    @Override  
                    public boolean onMenuItemClick(MenuItem item) {  
                        return true;  
                    }  
                });  
        subMenu.add("sub item 2").setIcon(R.drawable.ic_launcher)  
                .setOnMenuItemClickListener(new OnMenuItemClickListener() {  
                    @Override  
                    public boolean onMenuItemClick(MenuItem item) {  
                        return false;  
                    }  
                });  

	}

	public MyActionPrivider(Context context) {
		super(context);
	}

	@Override
	public View onCreateActionView() {
		// TODO Auto-generated method stub
		return null;
	}

}
