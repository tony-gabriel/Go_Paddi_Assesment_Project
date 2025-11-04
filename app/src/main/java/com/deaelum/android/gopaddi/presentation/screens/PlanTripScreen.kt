package com.deaelum.android.gopaddi.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.deaelum.android.gopaddi.R
import com.deaelum.android.gopaddi.data.model.Trip
import com.deaelum.android.gopaddi.data.model.TripCategory
import com.deaelum.android.gopaddi.presentation.components.PlanTripSection
import com.deaelum.android.gopaddi.presentation.components.TripItem
import com.deaelum.android.gopaddi.presentation.viewModel.TripViewModel
import com.deaelum.android.gopaddi.presentation.viewModel.TripsUiState
import com.deaelum.android.gopaddi.presentation.widgets.LoadingDialog

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanTripScreen(
    viewModel: TripViewModel = hiltViewModel(),
    onNavToViewDetail: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val uiState by viewModel.homeUiState.collectAsStateWithLifecycle()

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
    if (uiState is TripsUiState.Success) {
        trips = (uiState as TripsUiState.Success).trips as MutableList<Trip>
    }

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

        when (val state = uiState) {
            is TripsUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Outlined.Error,
                            contentDescription = "Error",
                            tint = Color.Red,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .size(30.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = state.message)
                    }
                }
            }

            TripsUiState.Loading -> {
                LoadingDialog(Modifier.padding(innerPadding))
            }

            is TripsUiState.Success -> {
                /*Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .safeContentPadding()
                        .fillMaxWidth()
                        .height(screenHeightDp)
                ) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {

                        item {
                            PlanTripSection(
                                Modifier.weight(1f),
                            )

                            Spacer(Modifier.height(80.dp))
                        }

                        item {
                            HeaderSection()
                        }

                        if (trips.isNotEmpty()) {
                            item {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("No trips create yet")
                                }
                            }
                        } else {
                            items(trips) { trip ->
                                TripItem(
                                    trip = trip,
                                    onClick = {
                                        viewModel.getTrip(trip.id)
                                        onNavToViewDetail()
                                    }
                                )
                            }
                        }


                    }
                }*/

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(innerPadding)

                ) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {

                        item {
                            PlanTripSection(
                                Modifier.height(screenHeightDp - 120.dp),
                            )
                        }

                        item { HeaderSection() }

                        items(trips) {
                            TripItem(
                                trip = it,
                                onClick = {
                                    viewModel.getTrip(it.id)
                                    onNavToViewDetail()
                                }
                            )
                        }

                    }

                }


            }
        }


        /*if (isLoading) {
            LoadingDialog(Modifier.padding(innerPadding))

        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(innerPadding)
                    .safeContentPadding()
            ) {
                if (allTrips != null) {
                    trips = allTrips as MutableList<Trip>
                }

                if (screenWidthDp > 600.dp){

                    Row(Modifier.fillMaxSize()) {
                        PlanTripSection(
                            Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                        )
                        Spacer(Modifier.width(10.dp))
                        YourTripsSection(
                            Modifier
                                .weight(1f)
                                .fillMaxHeight(),
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
        }*/

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderSection(modifier: Modifier = Modifier) {
    var selectedCategory by remember { mutableStateOf(TripCategory.TRIPS) }
    var expanded by remember { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(id = R.string.your_trip_title),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 23.dp, start = 16.dp)
        )
        Text(
            text = stringResource(id = R.string.your_trip_description),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            //modifier = Modifier.background(color = MaterialTheme.colorScheme.primaryContainer)
        ) {

            Box(
                modifier = Modifier
                    .wrapContentSize(Alignment.TopEnd)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.White)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedCategory.displayName,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )

                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowUp
                        else Icons.Default.KeyboardArrowDown,
                        contentDescription = "Open options",
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 8.dp
                        )

                    )
                }
            }

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                TripCategory.entries.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category.displayName) },
                        onClick = {
                            selectedCategory = category
                            expanded = false
                        }
                    )
                }
            }
        }

    }
}