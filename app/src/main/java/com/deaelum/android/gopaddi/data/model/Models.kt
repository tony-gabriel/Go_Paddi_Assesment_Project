package com.deaelum.android.gopaddi.data.model

import java.util.UUID

data class Country(
    val name: String,
    val locale: String,
    val flag: String,
)

data class Trip(
    var id: String = UUID.randomUUID().toString(),
    var name: String = "",
    var category: String = "",
    var description: String = "",
    var city: String = "",
    var startDate: String = "",
    var endDate: String = "",
)

enum class TripStyle(val displayName: String) {
    SOLO("Solo"),
    COUPLE("Couple"),
    FAMILY("Family"),
    GROUP("Group")
}

enum class TripCategory(val displayName: String) {
    TRIPS("Planned Trips"),
}