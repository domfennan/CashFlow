package com.example.cashflow;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class MyNotification {

    private static final int NOTIFICATION_PERMISSION_CODE = 1;

    public static void showNotification(Activity activity) {
        // Crie o Intent que será aberto ao clicar na notificação
        Intent intent = new Intent(activity, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(activity, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Construa a notificação
        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity, MainActivity.CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Lembrete!")
                .setContentText("Não se esqueça de adicionar seus gastos de hoje!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // Dispare a notificação
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(activity);

        // Verifique se a permissão de notificação foi concedida
        if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // Solicitar permissão em tempo de execução
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION_PERMISSION_CODE);
            return;
        }
        notificationManager.notify(0, builder.build());
    }
}
