package com.deaelum.android.gopaddi.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deaelum.android.gopaddi.data.model.Trip
import com.deaelum.android.gopaddi.domian.resources.Resources
import com.deaelum.android.gopaddi.domian.usecase.GetTripByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface TripDetailUiState {
    data class Success(val trip: Trip) : TripDetailUiState
    data object Loading : TripDetailUiState
    data class Error(val message: String) : TripDetailUiState
}


@HiltViewModel
class TripDetailsViewModel @Inject constructor (
    private val getTripByIdUseCase: GetTripByIdUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<TripDetailUiState>(TripDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()


    fun getSingleTripById(id: String){
        viewModelScope.launch {
            getTripByIdUseCase(id).collect { resources ->
                when(resources){
                    is Resources.Error -> {
                        _uiState.value = TripDetailUiState.Error(resources.message.toString())
                    }
                    is Resources.Loading -> {
                        _uiState.value = TripDetailUiState.Loading
                    }
                    is Resources.Success -> {

                        _uiState.value = TripDetailUiState.Success(resources.data as Trip)
                    }
                }
            }
        }
    }
}