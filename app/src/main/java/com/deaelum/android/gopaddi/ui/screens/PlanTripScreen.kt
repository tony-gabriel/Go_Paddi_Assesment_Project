package com.deaelum.android.gopaddi.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deaelum.android.gopaddi.R
import com.deaelum.android.gopaddi.ui.components.PlanTripSection
import com.deaelum.android.gopaddi.ui.components.TripItem
import com.deaelum.android.gopaddi.ui.components.YourTripsSection
import com.deaelum.android.gopaddi.ui.data.Trip
import com.deaelum.android.gopaddi.ui.data.TripCategory
import com.deaelum.android.gopaddi.viewModel.TripViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanTripScreen(modifier: Modifier = Modifier, viewModel: TripViewModel) {
    val configuration = LocalConfiguration.current

    var selectedCategory by remember { mutableStateOf(TripCategory.TRIPS) }
    var expanded by remember { mutableStateOf(false) }

    val screenHeightDp = configuration.screenHeightDp.dp
    val screenWidthDp = configuration.screenWidthDp.dp

    val trips = mutableListOf(
        Trip(
            name = "Trip to bahamas",
            category = "Solo",
            description = "Fun trip",
            city = "Qatar",
            startDate = LocalDate.parse("2025-08-29"),
            endDate = LocalDate.parse("2025-08-31"),
        ),

        Trip(
            name = "Trip to bahamas 2",
            category = "Solo",
            description = "Fun trip",
            city = "Qatar",
            startDate = LocalDate.parse("2025-08-29"),
            endDate = LocalDate.parse("2025-09-29"),
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .height(screenHeightDp)
                .verticalScroll(rememberScrollState())
                .background(Color.White)
            // Removed verticalArrangement = Arrangement.SpaceBetween
        ) {
            PlanTripSection(
                Modifier.height(screenHeightDp),
                viewModel
            )

            LazyColumn(Modifier
                .height(screenHeightDp)
                .fillMaxWidth()) {
                item {
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
                            modifier = Modifier.background(color = Color(0xFFF3F0F0))
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

                items(trips){
                    TripItem(Modifier.weight(1f),it)
                }
            }
        }
    }
}