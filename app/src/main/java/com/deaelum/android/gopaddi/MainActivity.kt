package com.deaelum.android.gopaddi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.deaelum.android.gopaddi.ui.theme.GoPaddiTheme
import com.deaelum.android.gopaddi.ui.screens.PlanTripScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoPaddiTheme {
                PlanTripScreen()
            }
        }
    }
}