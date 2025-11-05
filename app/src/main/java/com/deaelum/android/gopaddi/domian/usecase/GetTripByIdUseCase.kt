package com.deaelum.android.gopaddi.domian.usecase

import com.deaelum.android.gopaddi.domian.repository.TripRepository
import com.deaelum.android.gopaddi.domian.resources.Resources
import com.deaelum.android.gopaddi.data.model.Trip
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTripByIdUseCase @Inject constructor(private val tripRepository: TripRepository) {
    suspend operator fun invoke(id: String): Flow<Resources<Trip>> = tripRepository.getTripById(id)
}