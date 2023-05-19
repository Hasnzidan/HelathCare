package com.example.healthcare;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class NotificationService extends Service {
    static final String CHANNEL_ID = "AppointmentReminderChannel";
    private static final int NOTIFICATION_ID = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String title = intent.getStringExtra("title");
        String time = intent.getStringExtra("time");
        String date = intent.getStringExtra("date");
        String[] doctorDetails = intent.getStringArrayExtra("doctorDetails");

        showNotification(title, time, date, doctorDetails);

        stopSelf();
        return START_NOT_STICKY;
    }



    private void showNotification(String title, String time, String date, String[] doctorDetails) {
        Intent notificationIntent = new Intent(this, NotificationBooking.class);
        notificationIntent.putExtra("text2", doctorDetails[0]);
        notificationIntent.putExtra("text3", doctorDetails[1]);
        notificationIntent.putExtra("text4", doctorDetails[2]);
        notificationIntent.putExtra("text5", doctorDetails[3]);
        notificationIntent.putExtra("date", date);
        notificationIntent.putExtra("time", time);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Appointment Reminder")
                .setContentText(title + " on " + date + " at " + time)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Appointment Reminder Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(serviceChannel);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
