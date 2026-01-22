@file:OptIn(ExperimentalMaterial3Api::class)

package com.heartratemonitor.pregnancyvitalstrackerwithreminders.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.heartratemonitor.pregnancyvitalstrackerwithreminders.ui.screen.VitalsScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "vitals"
    ) {
        composable("vitals") {
            VitalsScreen()
        }
    }
}
