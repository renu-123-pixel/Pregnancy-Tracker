package com.heartratemonitor.pregnancyvitalstrackerwithreminders.worker

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.heartratemonitor.pregnancyvitalstrackerwithreminders.notification.NotificationHelper

class ReminderWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun doWork(): Result {
        NotificationHelper.showNotification(applicationContext)
        return Result.success()
    }
}
