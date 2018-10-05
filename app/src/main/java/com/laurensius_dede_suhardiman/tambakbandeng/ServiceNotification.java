package com.laurensius_dede_suhardiman.tambakbandeng;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.laurensius_dede_suhardiman.tambakbandeng.appcontroller.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class ServiceNotification extends Service {

    Timer timer = new Timer();
    Boolean init = true;
    String recent_notif = "";

    public ServiceNotification(){}

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("UNSUPPORTED OPERATION EXCEPTION");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final Handler handler = new Handler();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            loadNotification();
                        } catch (Exception e) {

                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 5000);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    public void loadNotification(){
        String tag_req_notification = "BANDENG_SERVICE";
        String url = getResources().getString(R.string.api_url);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response On Service : ", response.toString());
                        notificationChecker(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("TAMBAK ERROR", error.toString());
                    }
                });
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_req_notification);
    }

    void notificationChecker(JSONObject data){
        try {

            String val_suhu = data.getJSONArray("recent").getJSONObject(0).getString("val_suhu").toString();
            String val_kekeruhan = data.getJSONArray("recent").getJSONObject(0).getString("val_kekeruhan").toString();
            String val_kedalaman = data.getJSONArray("recent").getJSONObject(0).getString("val_kedalaman").toString();
            String cat_suhu = data.getJSONArray("recent").getJSONObject(0).getString("cat_suhu").toString();
            String cat_kekeruhan = data.getJSONArray("recent").getJSONObject(0).getString("cat_kekeruhan").toString();
            String cat_kedalaman = data.getJSONArray("recent").getJSONObject(0).getString("cat_kedalaman").toString();
            String id_notif = data.getJSONArray("notif").getJSONObject(0).getString("id").toString();
            if(!recent_notif.equals(id_notif) && !init){
                if(!cat_suhu.equals("Normal") || !cat_kekeruhan.equals("Normal") || !cat_kedalaman.equals("Normal")){
                    String str_notifikasi = data.getJSONArray("notif").getJSONObject(0).getString("pesan_notifikasi").toString();
                    Log.d("notifikasi ","Buat notifikasi");
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(getApplicationContext(), "notify_001");
                    Intent ii = new Intent(getApplicationContext(), TambakBandeng.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, ii, 0);
                    NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
                    bigText.bigText("Notifikasi");
                    bigText.setBigContentTitle("Notifikasi");
                    bigText.setSummaryText(str_notifikasi);
                    mBuilder.setContentIntent(pendingIntent);
                    mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
                    mBuilder.setContentTitle("Notifikasi");
                    mBuilder.setContentText(str_notifikasi);
                    mBuilder.setPriority(Notification.PRIORITY_MAX);
                    mBuilder.setStyle(bigText);
                    NotificationManager mNotificationManager =
                            (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("notify_001",
                                "Channel human readable title",
                                NotificationManager.IMPORTANCE_HIGH);
                        mNotificationManager.createNotificationChannel(channel);
                    }
                    mNotificationManager.notify(0, mBuilder.build());
                    recent_notif = str_notifikasi;
                }
            }
            recent_notif = id_notif;
            init = false;
        }catch (JSONException e){
            Log.d("Bandeng Notification", e.getMessage().toString());
        }
    }

}