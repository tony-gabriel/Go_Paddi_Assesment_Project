package com.deaelum.android.gopaddi.domian.usecase

import com.deaelum.android.gopaddi.domian.repository.TripRepository
import com.deaelum.android.gopaddi.domian.resources.Resources
import com.deaelum.android.gopaddi.data.model.Trip
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTripsUseCase @Inject constructor (private val tripsRepository: TripRepository) {
    suspend operator fun invoke(): Flow<Resources<List<Trip>>> = tripsRepository.getAllTrips()
}