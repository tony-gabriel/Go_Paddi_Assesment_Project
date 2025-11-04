package com.deaelum.android.gopaddi.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.deaelum.android.gopaddi.R
import com.deaelum.android.gopaddi.data.model.Trip
import com.deaelum.android.gopaddi.data.model.TripCategory
import com.deaelum.android.gopaddi.presentation.viewModel.TripViewModel

/*@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YourTripsSections(
    modifier: Modifier = Modifier,
    trips: List<Trip>,
    onNavigate: (String) -> Unit = {}
) {

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        HeaderSection()

        for (trip in trips) {
            TripItem(
                trip = trip,
                onClick = { onNavigate(trip.id) }
            )
        }

        *//*LazyColumn(
            modifier.fillMaxSize()
        ) {
            // item {  }

            if (trips.isEmpty()) {

                item {
                    Box(
                        modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No Trips created yet")
                    }
                }
            } else {

                items(trips) {

                    TripItem(
                        trip = it,
                        onClick = { onNavigate(it.id) }
                    )

                }
            }
        }*//*

    }
}*/

    /*@OptIn(ExperimentalMaterial3Api::class)
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
    }*/