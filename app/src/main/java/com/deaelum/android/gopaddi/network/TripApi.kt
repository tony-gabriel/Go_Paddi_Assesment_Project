package com.deaelum.android.gopaddi.network

import com.deaelum.android.gopaddi.ui.data.Trip
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.DELETE
import retrofit2.http.Path

interface TripApi {
    @GET("/api/trips")
    suspend fun getAllTrips(): Response<List<Trip>>

    @POST("/api/trips")
    suspend fun saveTrip(@Body trip: Trip): Response<Trip>

    @PATCH("/api/trips/{id}")
    suspend fun updateTrip(@Path("id") id: String, @Body trip: Trip): Response<Trip>

    @DELETE("/api/trips/{id}")
    suspend fun deleteTrip(@Path("id") id: String): Response<Unit>

    @GET("/api/trips/{id}")
    suspend fun getTripById(@Path("id") id: String): Response<Trip>
}
