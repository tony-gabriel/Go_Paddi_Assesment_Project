package com.deaelum.android.gopaddi.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deaelum.android.gopaddi.R
import com.deaelum.android.gopaddi.ui.data.Trip
import com.deaelum.android.gopaddi.ui.util.Utils.Constants.getFormatedDate
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PlanTripSection(modifier: Modifier = Modifier) {
    var city by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf<LocalDate?>(null) }
    var endDate by remember { mutableStateOf<LocalDate?>(null) }
    var showSelectCityBottomSheet by remember { mutableStateOf(false) }
    var showDateSelectorBottomSheet by remember { mutableStateOf(false) }
    var showCreateTripBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Gray.copy(alpha = 0.2f)),
        // verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.plan_trip_title),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 23.dp, start = 16.dp)
            )
            Text(
                text = stringResource(id = R.string.plan_trip_description),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )
        }

        Spacer(modifier.weight(1f))

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .clip(shape = RoundedCornerShape(5.dp))
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showSelectCityBottomSheet = true }
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F0F0)),
                border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f))
            ) {
                Row(
                    modifier = Modifier.padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = "Location icon",
                        modifier = Modifier.height(86.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Column(
                        modifier = Modifier.padding(start = 8.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.where_to),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = city.ifEmpty { stringResource(id = R.string.select_city) },
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp)
                        .clickable { showDateSelectorBottomSheet = true },
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F0F0)),
                    border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f))
                ) {
                    Row(
                        modifier = Modifier.padding(start = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.CalendarToday,
                            contentDescription = "Calendar icon",
                            modifier = Modifier.height(86.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Column(
                            modifier = Modifier.padding(start = 8.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.start_date),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = if (startDate == null) stringResource(id = R.string.enter_date)
                                else getFormatedDate(startDate),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 12.dp)
                        .clickable { showDateSelectorBottomSheet = true },
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F0F0)),
                    border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f))
                ) {
                    Row(
                        modifier = Modifier.padding(start = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.CalendarToday,
                            contentDescription = "Location icon",
                            modifier = Modifier.height(86.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Column(
                            modifier = Modifier.padding(start = 8.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.end_date),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = if (endDate == null) stringResource(id = R.string.enter_date)
                                else getFormatedDate(endDate),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            Button(
                onClick = { showCreateTripBottomSheet = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D6EFD)),
                enabled = city.isNotBlank() && (startDate != null && endDate != null)
            ) {
                Text(
                    text = stringResource(id = R.string.create_trip_button),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                )
            }

            if (showSelectCityBottomSheet) {
                SelectDatesBottomSheet(
                    onDismiss = { showSelectCityBottomSheet = false },
                    onSelectCountry = {
                        city = it
                        showSelectCityBottomSheet = false
                    }
                )
            }

            if (showDateSelectorBottomSheet) {
                SelectDatesBottomSheet(
                    onDismiss = { showDateSelectorBottomSheet = false },
                    onDateRangeSelected = { start, end ->
                        startDate = start
                        endDate = end
                        showDateSelectorBottomSheet = false
                    }
                )
            }

            if (showCreateTripBottomSheet) {
                CreateTripModalSheet(
                    onDismiss = { showCreateTripBottomSheet = false },
                    onCreateTrip = { name, category, description ->
                        val trip = Trip(name = name,
                            category = category,
                            description = description,
                            city = city,
                            startDate = startDate,
                            endDate = endDate
                        )
                        showCreateTripBottomSheet = false
                    }
                )
            }


        }

    }

}
