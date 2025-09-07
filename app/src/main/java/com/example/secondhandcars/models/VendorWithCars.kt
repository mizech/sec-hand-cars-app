package com.example.secondhandcars.models

import androidx.room.Embedded
import androidx.room.Relation

data class VendorWithCars(
    @Embedded
    val vendor: Vendor,
    @Relation(
        parentColumn = "vid",
        entityColumn = "vid"
    )
    val cars: List<Car>
)