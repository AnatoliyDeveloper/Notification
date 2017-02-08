package com.devandroid.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	private NotificationManager nm;
	private final int NOTIFICATION_ID = 127;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		nm = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
	}

	public void showNotification(View view){
		Notification.Builder builder = new Notification.Builder(getApplicationContext());

		Intent intent = new Intent(getApplicationContext(), FinishActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

		builder
				.setContentIntent(pendingIntent)
				.setSmallIcon(R.mipmap.ic_launcher)
				.setLargeIcon(BitmapFactory.decodeResource(getApplication().getResources(), R.mipmap.ic_launcher))
				.setTicker("New notification")
				.setWhen(System.currentTimeMillis())
				.setAutoCancel(true)
				.setContentTitle("Notification")
				.setContentText("Click to learn secret!")
				.setProgress(100, 20, true);

		Notification notification = builder.build();
//		notification.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
//		notification.defaults = Notification.DEFAULT_ALL;
//		notification.defaults = Notification.DEFAULT_VIBRATE;
//		notification.sound = Uri.parse("android.resource://com.devandroid.notification" + R.raw.my_sound);

//		long[] vibrate = new long[]{1200, 1500, 1200, 1500};
//		notification.vibrate = vibrate;

//		notification.flags = notification.flags | Notification.FLAG_ONGOING_EVENT;

		notification.defaults = Notification.DEFAULT_SOUND;
		notification.flags = notification.flags | Notification.FLAG_INSISTENT;

		nm.notify(NOTIFICATION_ID, notification);
	}

	public void cancelNotification(View view){
		nm.cancel(NOTIFICATION_ID);
	}
}
