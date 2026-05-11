package com.example.Moventra;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;

public class NotificationModel {
    private String title;
    private String message;
    private String time;
    private boolean isRead;

    // Constructor for data objects
    public NotificationModel(String title, String message, String time, boolean isRead) {
        this.title = title;
        this.message = message;
        this.time = time;
        this.isRead = isRead;
    }

    // Getters
    public String getTitle() { return title; }
    public String getMessage() { return message; }
    public String getTime() { return time; }
    public boolean isRead() { return isRead; }

    // System Notification Helper
    public static final String CHANNEL_ID = "car_channel";
    public static void showNotification(Context context, String title, String message) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Car Notifications", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);
        manager.notify((int) System.currentTimeMillis(), builder.build());
    }
}