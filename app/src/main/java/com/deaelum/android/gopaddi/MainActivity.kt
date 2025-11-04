package com.deaelum.android.gopaddi

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deaelum.android.gopaddi.ui.screens.PlanTripScreen
import com.deaelum.android.gopaddi.ui.screens.TripDetailScreen
import com.deaelum.android.gopaddi.ui.theme.GoPaddiTheme
import com.deaelum.android.gopaddi.viewModel.TripViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[TripViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            GoPaddiTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        PlanTripScreen(
                            viewModel = viewModel,
                            onNavToViewDetail = { navController.navigate("view_details") })
                    }
                    composable("view_details") {
                        TripDetailScreen(onNavBack = { navController.popBackStack() },
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}