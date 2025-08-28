package com.deaelum.android.gopaddi

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.deaelum.android.gopaddi.ui.theme.GoPaddiTheme
import com.deaelum.android.gopaddi.ui.screens.PlanTripScreen
import com.deaelum.android.gopaddi.viewModel.TripViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[TripViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            GoPaddiTheme {
                PlanTripScreen(viewModel = viewModel)
            }
        }
    }
}