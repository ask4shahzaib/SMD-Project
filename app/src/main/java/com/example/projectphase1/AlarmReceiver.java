package com.example.projectphase1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;

public class AlarmReceiver extends BroadcastReceiver{

    private final String CHANNEL_ID = "Personal Notification";
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context, HomePageTabScreen.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(HomePageTabScreen.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Personal Notification";
            String description = "NOtification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder b = new NotificationCompat.Builder(context, CHANNEL_ID);
        b.setContentTitle("E-Worker");
        b.setSmallIcon(R.drawable.app_icon);
        b.setContentText("Your request has been requested. Worker will contact you in 24 hours");
        b.setSound(alarmSound);
        b.setContentIntent(pendingIntent);
        b.setPriority(NotificationCompat.PRIORITY_MAX

        );

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1, b.build());




        //////////////
//        Notification.Builder builder = new Notification.Builder(context);
//
//        Notification notification = builder.setContentTitle("Demo App Notification")
//                .setContentText("New Notification From Demo App..")
//                .setTicker("New Message Alert!")
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentIntent(pendingIntent).build();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            builder.setChannelId(CHANNEL_ID);
//        }
//
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(
//                CHANNEL_ID,
//                "NotificationDemo",
//                IMPORTANCE_DEFAULT
//            );
//            notificationManager.createNotificationChannel(channel);
//        }
//
//        notificationManager.notify(0, notification);
    }
}
