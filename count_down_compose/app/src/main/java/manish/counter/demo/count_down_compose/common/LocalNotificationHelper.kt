package manish.counter.demo.count_down_compose.common

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import manish.counter.demo.count_down_compose.MainActivity
import manish.counter.demo.count_down_compose.R

class LocalNotificationHelper(private val context: Context)  {

    private val channelId = "timer_channel_id"

    init {
        createNotificationChannel()
    }

    fun checkIfHavePermission(): Boolean {
        return ActivityCompat
            .checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
    }

    private fun createNotificationChannel() {
        val name = "Counter Completion Notifications"
        val descriptionText = "Counter notifications"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            ContextCompat.getSystemService(context, NotificationManager::class.java)!!
        notificationManager.createNotificationChannel(channel)
    }

    fun showTimerNotification() {
        val notificationId = 1

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Completed counting.")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        if (checkIfHavePermission()) {
            NotificationManagerCompat.from(context).notify(notificationId, notification)
        }
    }

}