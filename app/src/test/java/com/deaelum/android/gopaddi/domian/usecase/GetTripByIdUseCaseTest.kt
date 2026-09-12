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

class GetTripByIdUseCaseTest {

    @Test
    fun `invoke delegates to repository with id and returns trip flow`() = runBlocking {
        val trip = testTrip(id = "trip-1")
        val expectedEmissions = listOf(
            Resources.Loading(),
            Resources.Success(trip),
        )
        val repository = FakeTripRepository(getTripByIdFlow = flowOf(*expectedEmissions.toTypedArray()))
        val useCase = GetTripByIdUseCase(repository)

        val emissions = useCase("trip-1").toList()

        assertEquals(expectedEmissions, emissions)
        assertEquals("trip-1", repository.requestedTripId)
    }

    private class FakeTripRepository(
        private val getTripByIdFlow: Flow<Resources<Trip>>,
    ) : TripRepository {
        var requestedTripId: String? = null

        override suspend fun createTrip(trip: Trip): Resources<Trip> = Resources.Success(trip)

        override suspend fun getAllTrips(): Flow<Resources<List<Trip>>> = emptyFlow()

        override suspend fun getTripById(id: String): Flow<Resources<Trip>> {
            requestedTripId = id
            return getTripByIdFlow
        }
    }
}

