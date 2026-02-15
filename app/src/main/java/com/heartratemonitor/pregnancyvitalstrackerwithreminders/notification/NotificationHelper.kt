package com.heartratemonitor.pregnancyvitalstrackerwithreminders.notification

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.heartratemonitor.pregnancyvitalstrackerwithreminders.MainActivity
import com.heartratemonitor.pregnancyvitalstrackerwithreminders.R

object NotificationHelper {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun showNotification(context: Context) {

        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )


            val notification = NotificationCompat.Builder(context, "vitals_channel")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Time to log your vitals!")
                .setContentText(
                    "Stay on top of your health. Please update your vitals now!"
                )
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

            NotificationManagerCompat.from(context)
                .notify(1, notification)

    }
}
