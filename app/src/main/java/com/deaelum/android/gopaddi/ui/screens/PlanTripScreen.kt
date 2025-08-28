package com.deaelum.android.gopaddi.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.deaelum.android.gopaddi.ui.components.PlanTripSection
import com.deaelum.android.gopaddi.ui.components.YourTripsSection
import com.deaelum.android.gopaddi.ui.data.Trip
import com.deaelum.android.gopaddi.viewModel.TripViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanTripScreen(modifier: Modifier = Modifier, viewModel: TripViewModel) {

    val trips = mutableListOf<Trip>()
//        Trip(
//            name = "Trip to bahamas",
//            category = "Solo",
//            description = "Fun trip",
//            city = "Qatar",
//            startDate = LocalDate.now(),
//            endDate = LocalDate.now(),
//        ),
//
//        Trip(
//            name = "Trip to bahamas 2",
//            category = "Solo",
//            description = "Fun trip",
//            city = "Qatar",
//            startDate = LocalDate.now(),
//            endDate = LocalDate.now(),
//        )
    //)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Plan a Trip",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.White),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            PlanTripSection(Modifier.weight(1f), viewModel)

            //YourTripsSection(trips = trips)
        }
    }
}