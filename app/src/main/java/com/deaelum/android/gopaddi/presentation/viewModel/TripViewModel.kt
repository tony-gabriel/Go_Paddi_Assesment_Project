package com.deaelum.android.gopaddi.presentation.viewModel

import android.util.Log
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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface TripsUiState {
    data class Success(val trips: List<Trip>) : TripsUiState
    data object Loading : TripsUiState
    data class Error(val message: String) : TripsUiState
}


@HiltViewModel
class TripViewModel @Inject constructor(
    private val createTripUseCase: CreateTripUseCase,
    private val getAllTripsUseCase: GetAllTripsUseCase,
) : ViewModel() {

    private val _homeUiState = MutableStateFlow<TripsUiState>(TripsUiState.Loading)
    val homeUiState = _homeUiState.asStateFlow()

    private val _isTripCreated = MutableStateFlow(false)
    val isTripCreated = _isTripCreated.asStateFlow()

    private val _tripCreatedError = MutableStateFlow("")
    val tripCreatedError = _tripCreatedError.asStateFlow()



    init {
        loadData()
    }

    fun loadData() {
        getAllTrips()
    }

    fun getAllTrips() {

        viewModelScope.launch {
            getAllTripsUseCase().collect { resources ->
                when (resources) {
                    is Resources.Error ->
                        _homeUiState.value =
                            TripsUiState.Error(resources.message ?: "An unexpected error occurred")

                    is Resources.Loading -> _homeUiState.value = TripsUiState.Loading

                    is Resources.Success -> _homeUiState.value =
                        TripsUiState.Success(resources.data ?: emptyList())
                }
            }

        }
    }

    fun createTrip(trip: Trip) {
        _homeUiState.value = TripsUiState.Loading

        viewModelScope.launch {
            createTripUseCase(trip).let { resources ->
                when (resources) {
                    is Resources.Error ->
                        _homeUiState.value =
                            TripsUiState.Error(resources.message ?: "An unexpected error occurred")

                    is Resources.Loading -> _homeUiState.value = TripsUiState.Loading

                    is Resources.Success -> _isTripCreated.value = true
                }

                if (resources is Resources.Success) {
                    _isTripCreated.value = true
                    loadData()
                } else if (resources is Resources.Error) {
                    _tripCreatedError.value = resources.message ?: "An unexpected error occurred"
                }
            }
        }

    }

    fun resetCreateTrip() {
        _isTripCreated.value = false
    }

    fun resetErrors() {
        _tripCreatedError.value = ""
    }
}