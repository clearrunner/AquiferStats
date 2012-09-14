package com.aquifer.stats;

import java.io.IOException;
import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class SummaryActivity extends Activity {
	// String currentLevel;
	// String pastLevel;
	// String stage;
	// String date;
	// Document doc;
	// String strStageLink;
	ProgressDialog dialog;
	String page;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dialog = ProgressDialog.show(SummaryActivity.this, "",
				"Loading. Please wait...", true);
		new getWebPage().execute("http://saws.org/");
		setContentView(R.layout.summary);

	}

	public void populateActivity() {
		TextView textview = new TextView(this);
		textview.setText("Level: " + DataObject.getInstance().currentLevel
				+ " | STAGE: " + DataObject.getInstance().stage + " | "
				+ DataObject.getInstance().date);

		replaceStringsHtml("flot_graph.html");
		loadWebview();
		dialog.hide();
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

		webview.loadUrl("http://www.edwardsaquifer.org/widget_conditions.php");
//		webview.loadUrl("javascript: (function() {$(document).ready(function(){$('a').click(function(event){alert('Thanks for visiting!'');});});})();");

	}

	public void replaceStringsHtml(String file) {
		page = convertStreamToString(file);
		page = page.replace("[AQUIFERLEVEL]",
				DataObject.getInstance().currentLevel);
//		System.out.println("***************PPPPPAAAGE*********************" + page);
	
		
		// try {
		// FileUtils.writeStringToFile(new File(file), page);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public String convertStreamToString(String file) {
		AssetManager assetManager = getAssets();
		InputStream stream = null;
		try {
			stream = assetManager.open(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return new java.util.Scanner(stream).useDelimiter("\\A").next();
		} catch (java.util.NoSuchElementException e) {
			return "";
		}
	}

	private class getWebPage extends AsyncTask<String, Integer, Boolean> {
		@Override
		protected Boolean doInBackground(String... params) {
			try {
//				System.err.println(DataObject.getInstance().doc);
//				System.err.println(params[0]);
				DataObject.getInstance().doc = Jsoup.connect(params[0]).get();
//				System.err.println("WIKI");
//				Element aqLevel = DataObject.getInstance().doc.select(
//						"span[class=head16]").first();
//				Element aqDate = aqLevel.nextElementSibling()
//						.nextElementSibling();

				// DataObject.getInstance().doc.select("span[class=Default10]").first();
//				Element stageLink = DataObject.getInstance().doc.select(
//						"a[href*=/conservation/aquifermgmt/stage]").first();
//				DataObject.getInstance().strStageLink = stageLink.attr("href")
//						.toString();
//				DataObject.getInstance().currentLevel = aqLevel.text();
//				DataObject.getInstance().date = aqDate.text();
//				DataObject.getInstance().stage = DataObject.getInstance().strStageLink
//						.toString()
//						.substring(
//								DataObject.getInstance().strStageLink
//										.indexOf("stage") + 5,
//								DataObject.getInstance().strStageLink
//										.indexOf("stage") + 6);

						DataObject.getInstance().strStageLink = "5"
								.toString();
						DataObject.getInstance().currentLevel = "650";
						DataObject.getInstance().date = "6/5";
						DataObject.getInstance().stage = "1";
				// Element aqDate = aqLevel.firstElementSibling();
				// Element aqDate2 =
				// doc.getElementsByIndexEquals(aqDate.elementSiblingIndex()).first();
				// System.err.println("DOC DATA " + doc.data());
				// System.err.println("SIBLING TAG NUM**************** " +
				// aqDate.elementSiblingIndex());
//				System.err.println("Stage**************** "
//						+ DataObject.getInstance().stage);
//				System.err.println("StageLink**************** "
//						+ stageLink.attr("href").toString());
//				System.err.println("Date**************** "
//						+ DataObject.getInstance().date);
//				System.err.println("AQUIFER LEVEL**************** "
//						+ DataObject.getInstance().currentLevel);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return true;
		}

		protected void onPostExecute(Boolean result) {
			populateActivity();
		}

	}

}
