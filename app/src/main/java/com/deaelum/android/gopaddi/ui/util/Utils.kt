package com.deaelum.android.gopaddi.ui.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Date

class Utils {
    object Constants {
        @RequiresApi(Build.VERSION_CODES.O)
        @JvmStatic
        fun getFormatedDate( date: LocalDate?): String {
            if (date == null) {
                return "Select Date"
            }

            val dateFromLocalDate: Date = Date.from(
                date.atStartOfDay(ZoneId.systemDefault()).toInstant()
            )


            return SimpleDateFormat("EEE, MMM dd", java.util.Locale.getDefault())
                .format(dateFromLocalDate)
        }
    }
}