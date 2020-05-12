package com.zero.servicio;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.zero.MainActivity;
import com.zero.R;
import com.zero.Request.RequestPostNotificacion;
import com.zero.model.Estudiante;
import com.zero.model.Notificacion;
import com.zero.retrofit.ApiRest;
import com.zero.sesion_manager.SesionManager;

import java.util.Date;
import androidx.core.app.NotificationCompat;
import com.zero.retrofit.Utilities;
import androidx.core.app.NotificationManagerCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationService extends FirebaseMessagingService {

    SesionManager sesionManager;
    ApiRest mAPIService;

    /*public NotificationService() {
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d("FCM",s);
    }*/
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String tituloD=remoteMessage.getData().get("TituloD");
        String descripcionD=remoteMessage.getData().get("DescripcionD");
        sesionManager = new SesionManager(getApplicationContext());
        Estudiante estudiante = new Estudiante();
        estudiante = sesionManager.GetEstudiante();
        AddNotificacion(tituloD,descripcionD,estudiante.get_id());
        showNotificacion(tituloD,descripcionD);
        Log.d("FCM",tituloD+"-"+descripcionD);
    }


    private void showNotificacion(String tituloD,String descripcionD){

        NotificationCompat.Builder notificationBuilder;
        String CHANNEL_ID = "my_channel_01";
        sesionManager=new SesionManager(getApplicationContext());


        if(sesionManager.getState()) {
            PendingIntent notifyPIntent = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);

            notificationBuilder = new NotificationCompat.Builder(this, "01")
                    .setContentTitle(tituloD)
                    .setSmallIcon(R.drawable.logo)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(descripcionD))
                    .setContentText(descripcionD)
                    .setAutoCancel(true)
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .setContentIntent(notifyPIntent)
                    .setChannelId(CHANNEL_ID);
        }
        else {
            Intent resultIntent = new Intent(this, MainActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addNextIntentWithParentStack(resultIntent);
            PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            notificationBuilder = new NotificationCompat.Builder(this, "01")
                    .setContentTitle(tituloD)
                    .setSmallIcon(R.drawable.logo)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(descripcionD))
                    .setContentText(descripcionD)
                    .setAutoCancel(true)
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .setContentIntent(pendingIntent)
                    .setChannelId(CHANNEL_ID);
        }
        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {       // For Oreo and greater than it, we required Notification Channel.
            CharSequence name = "My New Channel";                   // The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name, importance); //Create Notification Channel
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(1 , notificationBuilder.build());

    }

    private void AddNotificacion(String titulo,String descripcion,String estudianteID) {
        mAPIService= Utilities.getAPIService();
        mAPIService.AddNotificacion(new Notificacion(titulo,descripcion,new Date(),new Date(),"Activa",estudianteID)).enqueue(new Callback<RequestPostNotificacion>() {
            @Override
            public void onResponse(Call<RequestPostNotificacion> call, Response<RequestPostNotificacion> response) {
                Gson objetoConsola= new Gson();
                Log.i("Notificacion",objetoConsola.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<RequestPostNotificacion> call, Throwable t) {

            }
        });
    }
}
