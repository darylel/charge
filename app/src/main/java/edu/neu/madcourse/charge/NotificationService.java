package edu.neu.madcourse.charge;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificationService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.i("TOKEN", token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        String messageTitle = message.getNotification().getTitle();
        String messageBody = message.getNotification().getBody();
        String channelID = "CHECK_IN";

        NotificationChannel channel = new NotificationChannel(channelID, "Check In",
                NotificationManager.IMPORTANCE_HIGH);

        getSystemService(NotificationManager.class).createNotificationChannel(channel);

        Notification.Builder sticker = new Notification.Builder(this, channelID)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setSmallIcon(R.drawable.e5)
                .setAutoCancel(true);

        NotificationManagerCompat.from(this).notify(1, sticker.build());

        super.onMessageReceived(message);
    }
}
