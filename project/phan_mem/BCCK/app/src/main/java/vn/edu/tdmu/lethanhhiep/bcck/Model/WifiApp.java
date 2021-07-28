package vn.edu.tdmu.lethanhhiep.bcck.Model;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class WifiApp extends Application
{
    static WifiApp wifiApp;
    public static  final  String cb = "Thongbao";
    @Override
    public void onCreate()
    {
        super.onCreate();
        wifiApp = this;
    }
    public static synchronized WifiApp getInstance()
    {
        return wifiApp;
    }
    public void createNotificationChannels()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(
                    cb,"Thongbao",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationChannel.setLightColor(0xffffff);
            notificationChannel.setDescription("Thongbao");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
