package com.example.servicetest2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.Manifest
import android.annotation.SuppressLint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        startForeground(notificationID, createNotification())

        CoroutineScope(Dispatchers.IO).apply{
            launch {
                for (i in 1..10){
                    delay(1000)
                    updateNotification(notificationID, createNotification(i*10))
                }
                stopSelf(startId)
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private val channelID = "default"
    private val notificationID = 1

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(channelID, "default channel",
            NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = "description text of this channel."
        NotificationManagerCompat.from(this).createNotificationChannel(channel)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateNotification(id: Int, notification: Notification) {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
            == PackageManager.PERMISSION_GRANTED)
            NotificationManagerCompat.from(this).notify(id, notification)
    }
    private fun createNotification(progress: Int = 0) = NotificationCompat.Builder(this, channelID)
        .setContentTitle("Downloading")
        .setContentText("Downloading a file from a cloud")
        .setSmallIcon(R.drawable.ic_android_black_24dp)
        .setOnlyAlertOnce(true) // importance 에 따라 알림 소리가 날 때, 처음에만 소리나게 함 .setProgress(100, progress, false)
        .build()
}