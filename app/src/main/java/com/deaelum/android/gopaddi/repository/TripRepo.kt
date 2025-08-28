package com.deaelum.android.gopaddi.repository

import com.deaelum.android.gopaddi.network.TripApi
import com.deaelum.android.gopaddi.ui.data.Trip
import retrofit2.Response

class TripRepo(private val tripApi: TripApi) {

    suspend fun getAllTrips(): List<Trip> {
        return tripApi.getAllTrips()
    }

    suspend fun saveTrip(trip: Trip): Trip {
        return tripApi.saveTrip(trip)
    }

    suspend fun updateTrip(id: String, trip: Trip): Trip {
        return tripApi.updateTrip(id, trip)
    }

    suspend fun deleteTrip(id: String): Response<Unit> {
        return tripApi.deleteTrip(id)
    }

    suspend fun getTripById(id: String): Trip {
        return tripApi.getTripById(id)
    }
}
