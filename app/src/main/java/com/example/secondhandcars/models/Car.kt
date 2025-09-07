package com.example.secondhandcars.models

import android.R
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity = Vendor::class,
        parentColumns = arrayOf("vid"),
        childColumns = arrayOf("vid"),
        onDelete = ForeignKey.CASCADE)
])
data class Car(
    @PrimaryKey(autoGenerate = true)
    val cid: Long = 0,
    var name: String,
    var price: Double,
    var vid: Long
)