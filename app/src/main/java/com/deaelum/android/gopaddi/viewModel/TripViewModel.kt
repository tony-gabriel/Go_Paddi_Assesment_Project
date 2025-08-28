package com.deaelum.android.gopaddi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deaelum.android.gopaddi.network.NetworkResponse
import com.deaelum.android.gopaddi.network.RetrofitInstance
import com.deaelum.android.gopaddi.ui.data.Trip
import kotlinx.coroutines.launch

class TripViewModel: ViewModel() {
    private val tripApi = RetrofitInstance.tripApi
    private val _trips = MutableLiveData<NetworkResponse<Trip>>()
    val trips: LiveData<NetworkResponse<Trip>> = _trips

    private val _trip = MutableLiveData<NetworkResponse<Trip>>()

    fun getAllTrips() {
        _trips.value = NetworkResponse.Loading

        viewModelScope.launch {

            try {
                val response = tripApi.getAllTrips()

                if (response.isSuccessful) {
                    response.body()?.let {
                        _trips.value = NetworkResponse.Success(it) as NetworkResponse<Trip>?
                    }
                } else {
                    response.errorBody()?.let {
                        _trips.value = NetworkResponse.Error("${response.code()} $it")
                    }
                }

            } catch (e: Exception) {
                _trips.value = NetworkResponse.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun createTrip(trip: Trip){
        _trip.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {
                val response = tripApi.saveTrip(trip)

                if (response.isSuccessful) {
                    response.body()?.let {
                        _trip.value = NetworkResponse.Success(it) as NetworkResponse<Trip>?
                    }
                }else{
                    response.errorBody()?.let {
                        _trip.value = NetworkResponse.Error("${response.code()} $it")
                    }
                }


            }catch (e: Exception){
                _trip.value = NetworkResponse.Error(e.message ?: "Unknown error")
            }

        }

    }

}