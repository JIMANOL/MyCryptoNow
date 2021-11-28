package com.example.mycryptonow;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Looper.prepare();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(),remoteMessage.getNotification().getTitle(),Toast.LENGTH_LONG).show();

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext(), String.valueOf(1))
                        .setSmallIcon(R.drawable.logo_cryptos)
                        .setContentTitle(remoteMessage.getNotification().getTitle())
                        .setContentText(remoteMessage.getNotification().getBody())
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getBaseContext());
                notificationManager.notify(1, builder.build());
            }
        });
        Looper.loop();
    }

}
