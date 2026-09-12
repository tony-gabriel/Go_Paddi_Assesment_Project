package com.deaelum.android.gopaddi.domian.usecase

import com.deaelum.android.gopaddi.data.model.Trip
import com.deaelum.android.gopaddi.domian.repository.TripRepository
import com.deaelum.android.gopaddi.domian.resources.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class CreateTripUseCaseTest {

    @Test
    fun `invoke delegates to repository and returns created trip result`() = runBlocking {
        val trip = testTrip()
        val expectedResult = Resources.Success(trip)
        val repository = FakeTripRepository(createTripResult = expectedResult)
        val useCase = CreateTripUseCase(repository)

        val result = useCase(trip)

        assertSame(expectedResult, result)
        assertEquals(trip, repository.createdTrip)
    }

    private class FakeTripRepository(
        private val createTripResult: Resources<Trip>,
    ) : TripRepository {
        var createdTrip: Trip? = null

        override suspend fun createTrip(trip: Trip): Resources<Trip> {
            createdTrip = trip
            return createTripResult
        }

        override suspend fun getAllTrips(): Flow<Resources<List<Trip>>> = emptyFlow()

        override suspend fun getTripById(id: String): Flow<Resources<Trip>> = emptyFlow()
    }
}

