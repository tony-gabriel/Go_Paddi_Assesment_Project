package com.deaelum.android.gopaddi.domian.repository

import com.deaelum.android.gopaddi.domian.resources.Resources
import com.deaelum.android.gopaddi.data.model.Trip
import kotlinx.coroutines.flow.Flow

interface TripRepository {
    suspend fun createTrip(trip: Trip): Resources<Trip>
    suspend fun getAllTrips(): Flow<Resources<List<Trip>>>
    suspend fun getTripById(id: String): Resources<Trip>
}