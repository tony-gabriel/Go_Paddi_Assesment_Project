package com.deaelum.android.gopaddi.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.deaelum.android.gopaddi.R
import com.deaelum.android.gopaddi.ui.components.PlanTripSection
import com.deaelum.android.gopaddi.ui.components.TripItem
import com.deaelum.android.gopaddi.ui.components.YourTripsSection
import com.deaelum.android.gopaddi.ui.data.Trip
import com.deaelum.android.gopaddi.ui.data.TripCategory
import com.deaelum.android.gopaddi.ui.util.Utils
import com.deaelum.android.gopaddi.ui.util.Utils.Constants.showToast
import com.deaelum.android.gopaddi.ui.widgets.LoadingDialog
import com.deaelum.android.gopaddi.viewModel.TripViewModel
import java.time.LocalDate

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
                    .background(Color.White)
            ) {
                if (allTrips.value != null) {
                    trips = allTrips.value as MutableList<Trip>
                }

                LazyColumn {
                    item { PlanTripSection(
                        Modifier.height(screenHeightDp-120.dp),
                        viewModel
                    ) }

                    item { YourTripsSection(
                        Modifier.height(screenHeightDp-120.dp),
                        trips,
                        onNavigate = {id->
                            viewModel.getTrip(id)
                            onNavToViewDetail()
                        }
                    ) }
                }
            }
        }
    }

}