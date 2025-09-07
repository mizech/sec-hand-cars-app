package com.example.secondhandcars.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Vendor(
    @PrimaryKey(autoGenerate = true)
    val vid: Long = 0,
    var name: String,
    var country: String
)