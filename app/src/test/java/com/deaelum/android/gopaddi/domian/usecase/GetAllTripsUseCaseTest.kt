package com.deaelum.android.gopaddi.domian.usecase

import com.deaelum.android.gopaddi.data.model.Trip
import com.deaelum.android.gopaddi.domian.repository.TripRepository
import com.deaelum.android.gopaddi.domian.resources.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetAllTripsUseCaseTest {

    @Test
    fun `invoke delegates to repository and returns all trips flow`() = runBlocking {
        val trips = listOf(testTrip(id = "trip-1"), testTrip(id = "trip-2"))
        val expectedEmissions = listOf(
            Resources.Loading(),
            Resources.Success(trips),
        )
        val repository = FakeTripRepository(getAllTripsFlow = flowOf(*expectedEmissions.toTypedArray()))
        val useCase = GetAllTripsUseCase(repository)

        val emissions = useCase().toList()

        assertEquals(expectedEmissions, emissions)
        assertEquals(1, repository.getAllTripsCallCount)
    }

    private class FakeTripRepository(
        private val getAllTripsFlow: Flow<Resources<List<Trip>>>,
    ) : TripRepository {
        var getAllTripsCallCount = 0

        override suspend fun createTrip(trip: Trip): Resources<Trip> = Resources.Success(trip)

        override suspend fun getAllTrips(): Flow<Resources<List<Trip>>> {
            getAllTripsCallCount++
            return getAllTripsFlow
        }

        override suspend fun getTripById(id: String): Flow<Resources<Trip>> = emptyFlow()
    }
}

