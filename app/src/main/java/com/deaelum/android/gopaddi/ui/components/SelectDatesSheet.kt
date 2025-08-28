package com.deaelum.android.gopaddi.ui.components

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.deaelum.android.gopaddi.R
import com.deaelum.android.gopaddi.ui.util.Utils
import com.deaelum.android.gopaddi.ui.util.Utils.Constants.getFormatedDate
import com.deaelum.android.gopaddi.ui.util.Utils.Constants.showToast
import java.sql.Date
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDatesBottomSheet(
    startMonth: YearMonth = YearMonth.now(),
    monthsToShow: Int = 4,
    onDateRangeSelected: (LocalDate?, LocalDate?) -> Unit = { _, _ -> },
    onDismiss: () -> Unit = {}
) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var startDate by remember { mutableStateOf<LocalDate?>(null) }
    var endDate by remember { mutableStateOf<LocalDate?>(null) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                    )
                }

                Text(
                    text = "Date",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Divider()

            Column(
                modifier = Modifier.weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(top = 16.dp)
            ) {

                repeat(monthsToShow) { index ->
                    val ym =
                        startMonth.plusMonths(index.toLong())
                    MonthView(
                        yearMonth = ym,
                        startDate = startDate,
                        endDate = endDate,
                        onDayClick = { day ->
                            if (day.isBefore(LocalDate.now()) ){
                                showToast(context, "Select valid date")
                            }else {
                                if (startDate == null || (startDate != null && endDate != null)) {
                                    startDate = day
                                    endDate = null
                                    showToast(context, "Select end date")
                                } else if (startDate != null && endDate == null) {
                                    if (day.isBefore(startDate) ) {
                                        // swap
                                        endDate = startDate
                                        startDate = day
                                    } else {
                                        endDate = day
                                    }
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }


            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        DateColumn(Modifier.weight(1f),stringResource(id = R.string.start_date), startDate)
                        DateColumn(Modifier.weight(1f),stringResource(id = R.string.end_date), endDate)
                    }

                    Button(
                        onClick = { onDateRangeSelected(startDate, endDate) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D6EFD)),
                        enabled = startDate != null && endDate != null
                    ) {
                        Text(
                            text = stringResource(id = R.string.choose_date_button),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateColumn(modifier: Modifier = Modifier, label: String, value: LocalDate?) {
    Column(
       modifier = modifier,

    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
                .height(50.dp)
                .border(width = 1.dp, color = Color.Gray.copy(alpha = 0.5f), shape = RectangleShape)
                .clip(RoundedCornerShape(5.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = getFormatedDate(value),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Icon(
                imageVector = Icons.Outlined.CalendarToday,
                contentDescription = "Calendar icon",
                modifier = Modifier.padding(end = 16.dp).size(16.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun MonthView(
    yearMonth: YearMonth,
    startDate: LocalDate?,
    endDate: LocalDate?,
    onDayClick: (LocalDate) -> Unit
) {
    val firstDay = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        yearMonth.atDay(1)
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    val daysInMonth = yearMonth.lengthOfMonth()
    val days = (1..daysInMonth).map { firstDay.plusDays((it - 1).toLong()) }

    Column {
        // Month title
        Text(
            text = "${yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${yearMonth.year}",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Weekday headers
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            listOf("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su").forEach {
                Text(it, fontSize = 12.sp, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Calendar grid
        val firstDayOfWeekIndex = (firstDay.dayOfWeek.value % 7) // Monday=1 → 1, Sunday=7 → 0
        val emptyCells = List(firstDayOfWeekIndex) { null }
        val allDays = emptyCells + days

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.height(((allDays.size / 7) + 1) * 40.dp)
        ) {
            items(allDays) { date ->
                if (date == null) {
                    Box(modifier = Modifier.size(40.dp))
                } else {
                    val selected = (date == startDate || date == endDate)
                    val inRange = startDate != null && endDate != null && (date.isAfter(startDate) && date.isBefore(endDate))

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(2.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                when {
                                    selected -> Color(0xFF1E88E5)
                                    inRange -> Color(0xFF90CAF9)
                                    else -> Color.Transparent
                                }
                            )
                            .clickable { onDayClick(date) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = date.dayOfMonth.toString(),
                            fontSize = 14.sp,
                            color = if (selected) Color.White else Color.Black
                        )
                    }
                }
            }
        }
    }
}