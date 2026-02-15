package com.heartratemonitor.pregnancyvitalstrackerwithreminders

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.heartratemonitor.pregnancyvitalstrackerwithreminders.ui.navigation.NavGraph
import com.heartratemonitor.pregnancyvitalstrackerwithreminders.worker.ReminderWorker
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){
            isGranted : Boolean ->
        if(isGranted){
            scheduleReminder()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        askNotificationPermission()

        setContent {
            val navController = rememberNavController()
            NavGraph(navController)
        }
    }

    private fun askNotificationPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.POST_NOTIFICATIONS)== PackageManager.PERMISSION_GRANTED){
                scheduleReminder()
            }else{
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
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
