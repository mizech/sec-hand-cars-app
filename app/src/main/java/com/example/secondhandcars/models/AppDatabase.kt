package com.example.secondhandcars.models

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.secondhandcars.models.Car
import com.example.secondhandcars.models.CarDao
import com.example.secondhandcars.models.Vendor
import com.example.secondhandcars.models.VendorDao

@Database(version = 3, entities = [Vendor::class,  Car::class])
abstract class AppDatabase: RoomDatabase() {
    abstract fun vendorDao(): VendorDao

    abstract fun carDao(): CarDao
}