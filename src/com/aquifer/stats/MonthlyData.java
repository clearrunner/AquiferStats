package com.aquifer.stats;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MonthlyData extends Activity {
	String dataPageRain;
	
	
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summary);
		new getWebPage().execute("http://www.edwardsaquifer.org/raingauge_network.php");
		
    }
    
	public void loadWebview() {
		// Get a reference to the declared WebView holder
		WebView webview = (WebView) this.findViewById(R.id.webview);

		// Get the settings
		WebSettings webSettings = webview.getSettings();

		// Enable Javascript for interaction
		webSettings.setJavaScriptEnabled(true);

		// Make the zoom controls visible
		webSettings.setBuiltInZoomControls(false);

		// Allow for touching selecting/deselecting data series
		webview.requestFocusFromTouch();

		// Set the client
		webview.setWebViewClient(new WebViewClient());
		webview.setWebChromeClient(new WebChromeClient());

		// Load the URL
//		page="<html><head><script type='text/javascript' src='https://www.google.com/jsapi'></script><script type='text/javascript'>google.load('visualization', '1', {packages:['corechart']});google.setOnLoadCallback(drawChart);function drawChart() {var data = google.visualization.arrayToDataTable([['Year', 'Sales', 'Expenses'],['2004',  1000,      400],['2005',  1170,      460],['2006',  660,       1120],['2007',  1030,      540]]);var options = {title: 'Company Performance'};var chart = new google.visualization.LineChart(document.getElementById('chart_div'));chart.draw(data, options);}</script></head><body><div id='chart_div' style='width: 900px; height: 500px;'></div></body></html>";
//		webview.loadDataWithBaseURL("","goog.html" , "text/html", "utf-8", "");
		webview.loadUrl("file:///android_asset/rain.html");
//		webview.loadUrl("http://www.edwardsaquifer.org/raingauge_network.php");
//		webview.loadData(dataPageRain, "text/html; charset=UTF-8", null);

//		webview.loadUrl("javascript: (function() {$(document).ready(function(){$('a').click(function(event){alert('Thanks for visiting!'');});});})();");

	}
	
	
	private class getWebPage extends AsyncTask<String, Integer, Boolean> {
		@Override
		protected Boolean doInBackground(String... params) {
			try {
//				System.err.println(DataObject.getInstance().doc);
//				System.err.println(params[0]);
				
				DataObject.getInstance().doc = Jsoup.connect(params[0]).get();
				
//				System.err.println("GOT PAGE");
				
				Element data = DataObject.getInstance().doc.select("td[valign=top]").first();
//				System.err.println("DATA: " + data.html());
//				dataPageRain = data.html();
				
//				for(int i = 0;i< anArrayOfints.length; i++){
//					anArrayOfints[i] = i*3;
//				}


//				System.err.println("data**************** "
//						+ dataPageRain);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return true;
		}

		protected void onPostExecute(Boolean result) {
			loadWebview();
		}

	}
}
