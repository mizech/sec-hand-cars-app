package com.example.secondhandcars.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CarDao {
    @Insert
    suspend fun insert(car: Car)

    @Query("UPDATE car SET name=:name, price=:price, vid=:vid WHERE cid=:cid")
    suspend fun update(name: String, price: Double, vid: Long, cid: Long)

    @Query("SELECT * FROM car")
    suspend fun getAllCars(): List<Car>

    @Query("SELECT * FROM car WHERE cid = :id")
    suspend fun getCarByID(id: Long): Car

    @Query("DELETE FROM car WHERE cid = :id")
    suspend fun deleteCarByID(id: Long)
}