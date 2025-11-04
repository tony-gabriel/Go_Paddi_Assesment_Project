package com.deaelum.android.gopaddi.network

import android.util.Log
import com.deaelum.android.gopaddi.data.model.Trip
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import java.lang.Exception

class NetworkRequest {
    val gson = Gson()
    val tripsUrl = "https://caca18968819c215e3d8.free.beeceptor.com/api/trips"

    companion object{
        /*private val instance = NetworkRequest()
        fun getAllTrips(listener: GetTripsListener){
            instance.getAllTrips(listener)
        }
        fun createTrip(trip: Trip,listener: CreateTripListener){
            instance.createTrip(trip,listener)
        }
        fun getSingleTrip(id: String, listener: GetTripListener){
            instance.getSingleTrip(id, listener)
        }*/
    }


    fun getSingleTrip(id: String, listener: GetTripListener){
        val url = "$tripsUrl/$id"
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                val error = e.message ?: "Error occurred"
                listener.onError(error)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful){
                    if (response.body != null){
                        response.body?.let { body ->
                            try {
                                val trip = gson.fromJson(body.string(), Trip::class.java)
                                listener.onTripRetrieved(trip)
                            }catch (e: Exception){
                                val error = e.message ?: "Exception occurred"
                                listener.onError(error)
                                Log.i("Get Trip Error", error)
                            }
                        } ?: listener.onError("Response body is null")
                    }
                }else{
                    listener.onError("${response.code} Request failed")
                    Log.i("Get Trip Error", "Request failed with code: ${response.code}")
                }
            }
        })
    }

    fun createTrip(trip: Trip,listener: CreateTripListener){
        val requestBody = gson.toJson(trip).toRequestBody("application/json: charset=utf-8".toMediaTypeOrNull())
        val request = Request.Builder().url(tripsUrl).post(requestBody).build()

        val client = OkHttpClient().newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                val error = e.message ?: "Error occurred"
                listener.onError(error)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful){
                    if (response.body != null){
                        response.body?.let { body ->
                            listener.onTripCreated(true)
                        } ?: listener.onError("Error Occurred")
                    }
                }else{
                    listener.onError("${response.code} Request failed")
                    Log.i("Create Trip Error", "Request failed with code: ${response.code}")
                }
            }
        })
    }

    fun getAllTrips(listener: GetTripsListener){
        val request = Request.Builder().url(tripsUrl).build()

        val client = OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                val error = e.message ?: "Error occurred"
                listener.onError(error)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful){
                    if (response.body != null){
                        response.body?.let { body ->
                            val type = object : TypeToken<List<Trip>>() {}.type
                            try {
                                val trips = gson.fromJson<List<Trip>>(body.string(), type)
                                listener.onTripsRetrieved(trips)
                            }catch (e: Exception){
                                val error = e.message ?: "Exception occurred"
                                listener.onError(error)
                                Log.i("Get Trips Error", error)
                            }
                        } ?: listener.onError("Response body is null")
                    }
                }else{
                    listener.onError("${response.code} Request failed")
                    Log.i("Get Trips Error", "Request failed with code: ${response.code}")
                }
            }
        })


    }


    interface GetTripsListener {
        fun onTripsRetrieved(trips: List<Trip>)
        fun onError(message: String)

    }

    interface CreateTripListener {
        fun onTripCreated(onSuccess: Boolean)
        fun onError(message: String)

    }
    interface GetTripListener {
        fun onTripRetrieved(trip: Trip)
        fun onError(message: String)

    }

}