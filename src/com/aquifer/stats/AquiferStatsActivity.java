package com.aquifer.stats;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class AquiferStatsActivity extends TabActivity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		
		initSingletons();
		
		setupTabs();
		// Document doc = null;

		// sendNotification();

		// //////////////////////////////////////////////////////////////////////////////

		// ///////////////////////////////////////////////////////////////////////////////////

		// doc = Jsoup.connect("http://saws.org/our_water/aquifer/").get();
		// Element table = doc.select("table").get(6);
		// int count = 0;
		// while(itr.hasNext()) {
		// count++;
		// Element element = itr.next();
		// System.out.println("Value " + count +": " + element.text());
		//
		// }

	}

	public void setupTabs() {

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, SummaryActivity.class);

//		intent = new Intent();
		
		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("summary")
				.setIndicator("Summary",
						res.getDrawable(R.drawable.ic_tab_artists))
				.setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the other tabs
		intent = new Intent().setClass(this, ArtistsActivity2.class);
		spec = tabHost
				.newTabSpec("rain")
				.setIndicator("Rain",
						res.getDrawable(R.drawable.ic_tab_artists))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, MonthlyData.class);
		spec = tabHost
				.newTabSpec("monthly")
				.setIndicator("30 Day",
						res.getDrawable(R.drawable.ic_tab_artists))
				.setContent(intent);
		tabHost.addTab(spec);
		
		intent = new Intent().setClass(this, Settings.class);
		spec = tabHost
				.newTabSpec("settings")
				.setIndicator("Settings",
						res.getDrawable(R.drawable.ic_tab_artists))
				.setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
	}

	protected void initSingletons() {
		// Initialize the instance of MySingleton
		DataObject.initInstance();
	}

}