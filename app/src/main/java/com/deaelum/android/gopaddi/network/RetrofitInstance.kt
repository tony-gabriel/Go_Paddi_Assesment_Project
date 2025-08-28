package com.deaelum.android.gopaddi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    var baseUrl = "https://ca44f1e63f943f382fef.free.beeceptor.com"

    private fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val tripApi: TripApi by lazy {
        getInstance().create(TripApi::class.java)
    }
}