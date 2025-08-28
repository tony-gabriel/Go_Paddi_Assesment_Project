package com.deaelum.android.gopaddi.ui.data

import java.time.LocalDate
import java.util.UUID

data class Trip(
    var id: String = UUID.randomUUID().toString(),
    var name: String = "",
    var category: String = "",
    var description: String = "",
    var city: String = "",
    var startDate: LocalDate? = null,
    var endDate: LocalDate? = null,
)
