package com.heartratemonitor.pregnancyvitalstrackerwithreminders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.heartratemonitor.pregnancyvitalstrackerwithreminders.ui.navigation.NavGraph
import com.heartratemonitor.pregnancyvitalstrackerwithreminders.worker.ReminderWorker
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scheduleReminder()

        setContent {
            val navController = rememberNavController()
            NavGraph(navController)
        }
    }

    private fun scheduleReminder() {
        val workRequest =
            PeriodicWorkRequestBuilder<ReminderWorker>(
                5, TimeUnit.HOURS
            ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "vitals_reminder",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }
}
