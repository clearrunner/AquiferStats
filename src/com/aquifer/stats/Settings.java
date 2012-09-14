package com.aquifer.stats;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Settings extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView textview = new TextView(this);
		textview.setText("Sending Notification");
		setContentView(textview);
		sendNotification();

	}

	public void sendNotification() {

		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);

		int icon = R.drawable.water;
		CharSequence tickerText = DataObject.getInstance().currentLevel;
		long when = System.currentTimeMillis();

		Notification notification = new Notification(icon, tickerText, when);

		Context context = getApplicationContext();
		CharSequence contentTitle = "Edwards Aquifer Level " + DataObject.getInstance().currentLevel;
		CharSequence contentText = "Level: " + DataObject.getInstance().currentLevel + " | STAGE: "
				+ DataObject.getInstance().stage + " | " + DataObject.getInstance().date;

		Intent notificationIntent = new Intent(this, AquiferStatsActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);

		notification.setLatestEventInfo(context, contentTitle, contentText,
				contentIntent);

		final int AQUIFER_ID = 1;

		mNotificationManager.notify(AQUIFER_ID, notification);

	}
}
