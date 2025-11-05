package com.deaelum.android.gopaddi.util

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale

object Constants {
    const val BASE_URL = "https://ca6ccc589609e7d239b4.free.beeceptor.com/api/"

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFormatedDate(date: LocalDate?): String {
        if (date == null) {
            return "Select Date"
        }

        val dateFromLocalDate: Date = Date.from(
            date.atStartOfDay(ZoneId.systemDefault()).toInstant()
        )


        return SimpleDateFormat("EEE, MMM dd", Locale.getDefault())
            .format(dateFromLocalDate)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFullFormatedDate(date: LocalDate?): String {
        if (date == null) {
            return "N/A"
        }

        val dateFromLocalDate: Date = Date.from(
            date.atStartOfDay(ZoneId.systemDefault()).toInstant()
        )


        return SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            .format(dateFromLocalDate)
    }

    fun showToast(context: Context, msg: String) {
        Toast.makeText(
            context,
            msg,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun getErrorMsg(errorCode: Int): String = when (errorCode) {
        400 -> "Bad request. Please check your input."
        401 -> "Unauthorized. Please login again."
        403 -> "Forbidden. You don't have permission."
        404 -> "Resource not found."
        408 -> "Request timeout. Please try again."
        429 -> "Too many requests. Please wait."
        500 -> "Internal server error. Please try again later."
        502 -> "Bad gateway. Server is unreachable."
        503 -> "Service unavailable. Please try again later."
        504 -> "Gateway timeout. Please try again."
        else -> "Server error (${errorCode})"
    }
}