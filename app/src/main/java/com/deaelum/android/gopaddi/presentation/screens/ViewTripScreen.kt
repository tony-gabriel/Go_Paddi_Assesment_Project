package com.deaelum.android.gopaddi.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.deaelum.android.gopaddi.R
import com.deaelum.android.gopaddi.data.model.Trip
import com.deaelum.android.gopaddi.presentation.viewModel.TripDetailsState
import com.deaelum.android.gopaddi.util.Constants.getFullFormatedDate
import com.deaelum.android.gopaddi.presentation.widgets.ActivityCard
import com.deaelum.android.gopaddi.presentation.widgets.ItineraryCard
import com.deaelum.android.gopaddi.presentation.widgets.LoadingDialog
import com.deaelum.android.gopaddi.presentation.viewModel.TripViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripDetailScreen(
    viewModel: TripViewModel = hiltViewModel(),
    onNavBack: () -> Unit
) {
    val tripState by viewModel.trip.collectAsStateWithLifecycle()
    val trip = tripState?: Trip()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val getTripsError by viewModel.getTripsError.collectAsStateWithLifecycle()

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
                    IconButton(onClick = onNavBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {

            if (isLoading) {
                LoadingDialog(msg = "Loading...")
            }else{
                if (trip.city.isNotBlank()) {
                    TripPlannerScreen(trip)
                } else {
                    if (getTripsError.isNotBlank()) {
                        Box(
                            modifier = Modifier.fillMaxSize().padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(getTripsError)
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TripPlannerScreen(trip: Trip) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        item { HeroSection() }
        item { TripDetailsSection(trip) }
        item { PlanningActionsSection() }
        item { TripItinerariesSection() }
    }
}

@Composable
fun HeroSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_2),
            contentDescription = null,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TripDetailsSection(trip: Trip) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.CalendarToday,
                contentDescription = null,
                modifier = Modifier
                    .size(14.dp)
                    .padding(end = 2.dp)
            )
            Text(
                text =
                    "${getFullFormatedDate(LocalDate.parse(trip.startDate))} -> ${
                        getFullFormatedDate(
                            LocalDate.parse(trip.endDate)
                        )
                    }", //
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Text(
            text = trip.name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "${trip.city} | ${trip.category}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ActionButtonsRow()
    }
}

@Composable
fun ActionButtonsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedButton(
            onClick = { },
            border = BorderStroke(1.dp, Color(0xFF0D6EFD)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Outlined.CollectionsBookmark,
                    contentDescription = null,
                    tint = Color(0xFF0D6EFD)
                )
                Text(
                    text = "Trip Collaboration",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(0xFF0D6EFD)
                )
            }
        }

        OutlinedButton(
            onClick = { },
            border = BorderStroke(1.dp, Color(0xFF0D6EFD)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Icon(
                    imageVector = Icons.Outlined.Share,
                    contentDescription = null,
                    tint = Color(0xFF0D6EFD)
                )
                Text(
                    text = "Share Trip",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(0xFF0D6EFD)
                )
            }
        }

        IconButton(
            onClick = { },
            modifier = Modifier.size(40.dp),

            ) {
            Icon(
                imageVector = Icons.Default.MoreHoriz,
                contentDescription = "More options",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun PlanningActionsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        ActivityCard(
            title = "Activities",
            description = "Build, personalize, and optimize your itineraries with our trip planner",
            backgroundColor = Color(0xFF000031),
            buttonColor = Color(0xFF0D6EFD),
            buttonText = "Add Activities",
            textColor = Color.White
        )

        ActivityCard(
            title = "Hotels",
            description = "Build, personalize, and optimize your itineraries with our trip planner",
            backgroundColor = Color(0xFFE7F0FF),
            buttonColor = Color(0xFF0D6EFD),
            buttonText = "Add Hotels",
            textColor = Color.Black
        )

        ActivityCard(
            title = "Flights",
            description = "Build, personalize, and optimize your itineraries with our trip planner",
            backgroundColor = Color(0xFF0D6EFD),
            buttonColor = Color.White,
            buttonText = "Add Flights",
            textColor = Color.White,
            buttonTextColor = Color(0xFF0D6EFD)
        )
    }
}

@Composable
fun TripItinerariesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Trip Itineraries",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Your trip itineraries are placed here",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ItineraryCard(
                title = "Flights",
                titleColor = Color.Black,
                buttonText = "Add Flight",
                backgroundColor = Color(0xFFF0F2F5),
            )

            ItineraryCard(
                title = "Hotels",
                buttonText = "Add Hotel",
                backgroundColor = Color(0xFF344054),
            )

            ItineraryCard(
                title = "Activities",
                buttonText = "Add Activity",
                backgroundColor = Color(0xFF0054E4),
            )
        }
    }
}
