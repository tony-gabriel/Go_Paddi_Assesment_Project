package com.deaelum.android.gopaddi.data.api

import com.deaelum.android.gopaddi.data.model.Trip
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TripApiService {
    @GET("trips")
    suspend fun getTrips(): Response<List<Trip>>

    @POST("trips")
    suspend fun createTrip(@Body trip: Trip): Response<Trip>

    @GET("trips/{id}")
    suspend fun getTripById(@Path("id") id: String): Response<Trip>
}
