package com.deaelum.android.gopaddi.data.repository

import com.deaelum.android.gopaddi.domian.repository.TripRepository
import com.deaelum.android.gopaddi.domian.resources.Resources
import com.deaelum.android.gopaddi.data.api.TripApiService
import com.deaelum.android.gopaddi.data.model.Trip
import com.deaelum.android.gopaddi.util.Constants.getErrorMsg
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DataSourceRepositoryImpl @Inject constructor(
    private val apiService: TripApiService
) : TripRepository {
    override suspend fun createTrip(trip: Trip): Resources<Trip> {
        return try {
            val response = apiService.createTrip(trip)
            if (response.isSuccessful){
                val trip = response.body()
                if (trip != null) Resources.Success(trip)
                else Resources.Error("Failed to create trip")
            }
            else Resources.Error(getErrorMsg(response.code()))
        } catch (e: HttpException) {
            Resources.Error(getErrorMsg(e.code()))
        } catch (e: IOException) {
            Resources.Error("Check your internet connection")
        } catch (e: Exception) {
            Resources.Error(e.message?:"An unexpected error occurred")
        }
    }

    override suspend fun getAllTrips(): Flow<Resources<List<Trip>>> = flow {
        try {
            emit(Resources.Loading())

            val response = apiService.getTrips()
            if (response.isSuccessful) {
                val trips = response.body()

                if (trips != null) emit(Resources.Success(trips))
                else emit(Resources.Error("Failed to fetch trips"))

            } else {
                emit(Resources.Error(getErrorMsg(response.code())))
            }

        } catch (e: HttpException) {
            emit(Resources.Error(getErrorMsg(e.code())))
        } catch (e: IOException) {
            emit(Resources.Error("Check your internet connection"))
        } catch (e: Exception) {
            emit(Resources.Error(e.message?:"An unexpected error occurred"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getTripById(id: String): Flow<Resources<Trip>> = flow {
        try {
            emit(Resources.Loading())
            val response = apiService.getTripById(id)
            if (response.isSuccessful) {
                val trip = response.body()
                if (trip != null) emit(Resources.Success(trip))
                else emit(Resources.Error("Trip not found"))

            } else emit(Resources.Error(getErrorMsg(response.code())))
        } catch (e: HttpException) {
            emit(Resources.Error(getErrorMsg(e.code())))
        } catch (e: IOException) {
            emit(Resources.Error("Check your internet connection"))
        } catch (e: Exception) {
            emit(Resources.Error("An unexpected error occurred"))
        }
    }.flowOn(Dispatchers.IO)

}