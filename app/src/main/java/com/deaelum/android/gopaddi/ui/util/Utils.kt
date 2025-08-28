package com.deaelum.android.gopaddi.ui.util

import android.content.Context
import android.os.Build
import android.widget.Toast
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
                return ""
            }

            val dateFromLocalDate: Date = Date.from(
                date.atStartOfDay(ZoneId.systemDefault()).toInstant()
            )


            return SimpleDateFormat("EEE, MMM dd", java.util.Locale.getDefault())
                .format(dateFromLocalDate)
        }
        @RequiresApi(Build.VERSION_CODES.O)
        @JvmStatic
        fun getFullFormatedDate(date: LocalDate?): String {
            if (date == null) {
                return "N/A"
            }

            val dateFromLocalDate: Date = Date.from(
                date.atStartOfDay(ZoneId.systemDefault()).toInstant()
            )


            return SimpleDateFormat("ddd MMMMM yyyy", java.util.Locale.getDefault())
                .format(dateFromLocalDate)
        }

        @JvmStatic
        fun showToast(context: Context, msg: String){
            Toast.makeText(
                context,
                msg,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}