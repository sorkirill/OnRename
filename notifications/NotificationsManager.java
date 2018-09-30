package com.example.onrename.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.onrename.BaseActivity;
import com.example.onrename.MainActivity;
import com.example.onrename.R;


public class NotificationsManager {

    private static final String CHANNEL_ID = "Shedule";

    private static Notification createNotification ( Context context, String title, String text){


        Intent resultIntent = new Intent(context, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(context, CHANNEL_ID)
           .setSmallIcon(R.drawable.ic_directions_run_black_24dp)
                .setContentTitle(title)
                .setContentText(text)
                //.setStyle(new NotificationCompat.BigTextStyle().bigText(text))
                .setContentIntent(resultPendingIntent)
                .setAutoCancel(true)
                .build();

    }
    private static  void createChannel (Context context)
    {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);


        }
    }


    public static void showNotification (Context context, int id, String title, String text){

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(CHANNEL_ID)==null){
                createChannel(context);
            }
        }
        notificationManager.notify(id, createNotification(context, title, text));
    }
}
