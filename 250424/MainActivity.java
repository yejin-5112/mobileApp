package com.example.eggtimer;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private static final String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.edit);
        createNotificationChannel();
        requestNotificationPermission();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "My Notifications",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Egg Timer completion notification");
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }
    }

    public void sendNotification() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Egg Timer")
                .setContentText("계란 삶기가 완료되었습니다.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(NOTIFICATION_ID, builder.build());
        }
    }

    public void startTimer(View view) {
        String input = mEditText.getText().toString();
        if (input.length() < 5) {
            mEditText.setText("00:10");
            input = "00:10";
        }

        int min = Integer.parseInt(input.substring(0, 2));
        int sec = Integer.parseInt(input.substring(3, 5));
        int totalMillis = (min * 60 + sec) * 1000;

        new CountDownTimer(totalMillis, 1000) {
            public void onTick(long millisUntilFinished) {
                int remainingSeconds = (int) (millisUntilFinished / 1000);
                int displayMin = remainingSeconds / 60;
                int displaySec = remainingSeconds % 60;
                mEditText.setText(String.format("%02d:%02d", displayMin, displaySec));
            }

            public void onFinish() {
                mEditText.setText("done!");
                sendNotification();
            }
        }.start();
    }
}
