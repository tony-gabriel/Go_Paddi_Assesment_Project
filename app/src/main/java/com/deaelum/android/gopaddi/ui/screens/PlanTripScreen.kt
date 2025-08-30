package com.deaelum.android.gopaddi.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.deaelum.android.gopaddi.ui.components.PlanTripSection
import com.deaelum.android.gopaddi.ui.components.YourTripsSection
import com.deaelum.android.gopaddi.ui.data.Trip
import com.deaelum.android.gopaddi.ui.widgets.LoadingDialog
import com.deaelum.android.gopaddi.viewModel.TripViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanTripScreen(viewModel: TripViewModel, onNavToViewDetail: ()-> Unit) {
    val configuration = LocalConfiguration.current
    val context = LocalContext.current

    val allTrips = viewModel.trips.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)

    val screenHeightDp = configuration.screenHeightDp.dp
    val screenWidthDp = configuration.screenWidthDp.dp

    var trips = mutableListOf(
        Trip(
            name = "Trip to bahamas",
            category = "Solo",
            description = "Fun trip",
            city = "Qatar",
            startDate = "2025-08-29",
            endDate = "2025-08-31",
        ),

        Trip(
            name = "Trip to bahamas 2",
            category = "Solo",
            description = "Fun trip",
            city = "Qatar",
            startDate = "2025-08-29",
            endDate = "2025-09-29",
        )
    )

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
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        if (isLoading) {
            LoadingDialog(Modifier.padding(innerPadding))

        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
                    .height(screenHeightDp)
            ) {
                if (allTrips.value != null) {
                    trips = allTrips.value as MutableList<Trip>
                }

                if (screenWidthDp > 600.dp){

                    Row(Modifier.fillMaxSize()) {
                        PlanTripSection(
                            Modifier.weight(1f).height(screenHeightDp-120.dp),
                            viewModel
                        )
                        Spacer(Modifier.width(10.dp))
                        YourTripsSection(
                            Modifier.weight(1f).height(screenHeightDp-120.dp),
                            trips,
                            onNavigate = {id->
                                viewModel.getTrip(id)
                                onNavToViewDetail()
                            }
                        )
                    }

                }else {

                    LazyColumn {
                        item {
                            PlanTripSection(
                                Modifier.height(screenHeightDp - 120.dp),
                                viewModel
                            )
                        }

                        item {
                            YourTripsSection(
                                Modifier.height(screenHeightDp - 120.dp),
                                trips,
                                onNavigate = { id ->
                                    viewModel.getTrip(id)
                                    onNavToViewDetail()
                                }
                            )
                        }
                    }
                }
            }
        }
    }

}