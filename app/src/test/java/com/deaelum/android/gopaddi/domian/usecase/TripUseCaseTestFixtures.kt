package com.deaelum.android.gopaddi.domian.usecase

import com.deaelum.android.gopaddi.data.model.Trip

fun testTrip(
    id: String = "trip-1",
    name: String = "Lagos Weekend",
    category: String = "Planned Trips",
    description: String = "A short city break",
    city: String = "Lagos",
    startDate: String = "2026-10-01",
    endDate: String = "2026-10-03",
) = Trip(
    id = id,
    name = name,
    category = category,
    description = description,
    city = city,
    startDate = startDate,
    endDate = endDate,
)
