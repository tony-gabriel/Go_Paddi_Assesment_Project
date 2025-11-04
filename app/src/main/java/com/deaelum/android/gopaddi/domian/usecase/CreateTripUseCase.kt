package com.deaelum.android.gopaddi.domian.usecase

import com.deaelum.android.gopaddi.domian.repository.TripRepository
import com.deaelum.android.gopaddi.domian.resources.Resources
import com.deaelum.android.gopaddi.data.model.Trip
import javax.inject.Inject

class CreateTripUseCase @Inject constructor (private val tripRepository: TripRepository){
    suspend operator fun invoke(trip: Trip): Resources<Trip> = tripRepository.createTrip(trip)
}
