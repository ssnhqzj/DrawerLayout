package com.example.drawerlayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.drawerlayout.fragment.OneFragment;
import com.example.drawerlayout.fragment.TwoFragment;
import com.example.drawerlayout.tablistener.TabListener;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	private ListView listView;
	ActionProvider provider ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_LEFT_ICON);
		//setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_launcher);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		setOverflowShowingAlways();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		Tab tab = actionBar
				.newTab()
				.setText("Tab1")
				.setTabListener(new TabListener<OneFragment>(this, "tabone", OneFragment.class));
		actionBar.addTab(tab);
		
		tab = actionBar
				.newTab()
				.setText("Tab2")
				.setTabListener(new TabListener<TwoFragment>(this,"tabtwo",TwoFragment.class));
		actionBar.addTab(tab);
		
		List<Map<String,String>> data = new ArrayList<Map<String,String>>();
		for(int i=0; i< 10; i++){
			Map<String,String> map = new HashMap<String, String>();
			map.put("title", "title" + i);
			data.add(map);
		}
		
		listView = (ListView)findViewById(R.id.left_drawer);
		SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), data, R.layout.tv_main, new String[]{"title"}, new int[]{R.id.tv_main_1});
		listView.setAdapter(simpleAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				
				HashMap<String,String> map = (HashMap)arg0.getItemAtPosition(arg2);
				String itemText = map.get("title");
				//Toast.makeText(getApplicationContext(), itemText, Toast.LENGTH_SHORT).show();
				TextView main_content_tv1 = (TextView)findViewById(R.id.main_content_tv1);
				main_content_tv1.setText(itemText);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem shareItem = menu.findItem(R.id.action_share);  
		provider = MenuItemCompat.getActionProvider(shareItem);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	private void setOverflowShowingAlways() {   
	    try {   
	        ViewConfiguration config = ViewConfiguration.get(this);   
	        Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");   
	        menuKeyField.setAccessible(true);   
	        menuKeyField.setBoolean(config, false);   
	    } catch (Exception e) {   
	        e.printStackTrace();   
	    }   
	 
	}  
	
	private boolean setShareIntent() {  
		        if (provider != null) {  
		            Intent shareIntent = new Intent(Intent.ACTION_SEND);  
		            shareIntent.setType("text/plain");  
		            shareIntent.putExtra(Intent.EXTRA_TEXT, "这是要发送的文本");  
		              
		            PackageManager pm = getPackageManager();              
		            //检查手机上是否存在可以处理这个动作的应用  
		            List<ResolveInfo> infolist = pm.queryIntentActivities(shareIntent, 0);  
		            if (!infolist.isEmpty()) {  
		            	//((ShareActionProvider) provider).setShareIntent(shareIntent);  
		            	
		                return true;  
		            }  
		           return false;  
		        }  
		        return false;  
		    }  



}
