package com.deaelum.android.gopaddi.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deaelum.android.gopaddi.domian.resources.Resources
import com.deaelum.android.gopaddi.domian.usecase.CreateTripUseCase
import com.deaelum.android.gopaddi.domian.usecase.GetAllTripsUseCase
import com.deaelum.android.gopaddi.domian.usecase.GetTripByIdUseCase
import com.deaelum.android.gopaddi.data.model.Trip
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface TripsUiState {
    data class Success(val trips: List<Trip>) : TripsUiState
    data object Loading : TripsUiState
    data class Error(val message: String) : TripsUiState
}

data class TripDetailsState(
    val isLoading: Boolean = false,
    val trip: Trip? = null,
    val error: String = ""
)

@HiltViewModel
class TripViewModel @Inject constructor(
    private val createTripUseCase: CreateTripUseCase,
    private val getAllTripsUseCase: GetAllTripsUseCase,
    private val getSingleTripUseCase: GetTripByIdUseCase
) : ViewModel() {

    private val _homeUiState = MutableStateFlow<TripsUiState>(TripsUiState.Loading)
    val homeUiState = _homeUiState.asStateFlow()

    private val _tripDetailsState = MutableStateFlow(TripDetailsState())
    val tripDetailsState = _tripDetailsState.asStateFlow()

    private val _trip = MutableStateFlow<Trip?>(Trip())
    val trip = _trip.asStateFlow()



    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isTripCreated = MutableStateFlow(false)
    val isTripCreated = _isTripCreated.asStateFlow()

    private val _tripCreatedError = MutableStateFlow("")
    val tripCreatedError = _tripCreatedError.asStateFlow()


    private val _getTripsError = MutableStateFlow("")
    val getTripsError = _getTripsError.asStateFlow()


    init {
        loadData()
    }

    fun loadData() {
        getAllTrips()
    }

    fun getTrip(id: String) {
        viewModelScope.launch {
            _isLoading.value = true

            getSingleTripUseCase(id).let { resources ->

                /*when(resources){
                    is Resources.Error ->
                        _getTripsError.value = resources.message?:"An unexpected error occurred"

                    is Resources.Loading -> _isLoading.value = true

                    is Resources.Success -> _trip.value = resources.data
                }*/



                if (resources is Resources.Success) {
                    _trip.value = resources.data
                    _isLoading.value = false
                }

                /*NetworkRequest.getSingleTrip(id, object : NetworkRequest.GetTripListener{
                    override fun onTripRetrieved(trip: Trip) {
                        _trip.postValue(trip)
                        _isLoading.postValue(false)
                    }

                    override fun onError(message: String) {
                        _getTripsError.postValue(message)
                        _isLoading.postValue(false)
                    }
                })*/
            }
        }

    }

    fun getAllTrips() {
      //  _isLoading.postValue(true)

        viewModelScope.launch {
            getAllTripsUseCase().collect { resources ->
                when (resources) {
                    is Resources.Error<*> ->
                        _homeUiState.value = TripsUiState.Error(resources.message?:"An unexpected error occurred")

                    is Resources.Loading -> _homeUiState.value = TripsUiState.Loading

                    is Resources.Success -> _homeUiState.value = TripsUiState.Success(resources.data?: emptyList())
                }
            }

            /*NetworkRequest.getAllTrips(object : NetworkRequest.GetTripsListener {
                override fun onTripsRetrieved(trips: List<Trip>) {
                    _trips.postValue(trips)
                    _isLoading.postValue(false)
                }

                override fun onError(message: String) {
                   _getTripsError.postValue(message)
                    _isLoading.postValue(false)
                }
            })*/
        }
    }

    fun createTrip(trip: Trip) {
        _isLoading.value = true

        viewModelScope.launch {
            createTripUseCase(trip).let { resources ->
                if (resources is Resources.Success) {
                    _isTripCreated.value = true
                    _isLoading.value = false
                } else if (resources is Resources.Error) {
                    _tripCreatedError.value = resources.message?:"An unexpected error occurred"
                    _isLoading.value = false
                }
            }
        }

        /* NetworkRequest.createTrip(trip, object : NetworkRequest.CreateTripListener {
             override fun onTripCreated(onSuccess: Boolean) {
                 _isLoading.postValue(false)
                 _isTripCreated.postValue(onSuccess)
                 loadData()
             }

             override fun onError(message: String) {
                 _isLoading.postValue(false)
                 _tripCreatedError.postValue(message)
             }
         })*/
    }

    fun resetCreateTrip() {
        _isTripCreated.value = false
    }

    fun resetErrors() {
        _tripCreatedError.value = ""
        _getTripsError.value = ""
    }
}