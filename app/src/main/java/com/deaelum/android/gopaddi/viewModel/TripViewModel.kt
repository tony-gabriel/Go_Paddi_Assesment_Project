package com.deaelum.android.gopaddi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deaelum.android.gopaddi.network.NetworkRequest
import com.deaelum.android.gopaddi.ui.data.Trip
import kotlinx.coroutines.launch

class TripViewModel: ViewModel() {
    private val _trips = MutableLiveData<List<Trip>>()
    val trips: LiveData<List<Trip>> = _trips

    private val _trip = MutableLiveData<Trip>()
    val trip: LiveData<Trip> = _trip

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isTripCreated = MutableLiveData(false)
    val isTripCreated: LiveData<Boolean> = _isTripCreated

    private val _tripCreatedError = MutableLiveData("")
    val tripCreatedError: LiveData<String> = _tripCreatedError

    private val _getTripsError = MutableLiveData("")
    val getTripsError: LiveData<String> = _getTripsError


    init {
        loadData()
    }

    fun loadData(){
        getAllTrips()
    }

    fun getTrip(id: String){
        viewModelScope.launch {
            _isLoading.postValue(true)

            NetworkRequest.getSingleTrip(id, object : NetworkRequest.GetTripListener{
                override fun onTripRetrieved(trip: Trip) {
                    _trip.postValue(trip)
                    _isLoading.postValue(false)
                }

                override fun onError(message: String) {
                    _getTripsError.postValue(message)
                    _isLoading.postValue(false)
                }
            })
        }
    }

    fun getAllTrips() {
        _isLoading.postValue(true)

        viewModelScope.launch {
            NetworkRequest.getAllTrips(object : NetworkRequest.GetTripsListener {
                override fun onTripsRetrieved(trips: List<Trip>) {
                    _trips.postValue(trips)
                    _isLoading.postValue(false)
                }

                override fun onError(message: String) {
                   _getTripsError.postValue(message)
                    _isLoading.postValue(false)
                }
            })
        }
    }

    fun createTrip(trip: Trip){
        _isLoading.postValue(true)

        NetworkRequest.createTrip(trip, object : NetworkRequest.CreateTripListener {
            override fun onTripCreated(onSuccess: Boolean) {
                _isLoading.postValue(false)
                _isTripCreated.postValue(onSuccess)
                loadData()
            }

            override fun onError(message: String) {
                _isLoading.postValue(false)
                _tripCreatedError.postValue(message)
            }
        })
    }

    fun resetCreateTrip(){
        _isTripCreated.value = false
    }

    fun resetErrors(){
        _tripCreatedError.value = ""
        _getTripsError.value = ""
    }

}