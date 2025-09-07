package com.example.secondhandcars.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.secondhandcars.models.VendorWithCars

@Dao
interface VendorDao {
    @Insert
    suspend fun insert(vendor: Vendor)

    @Query("SELECT * FROM vendor")
    suspend fun getAllVendors(): List<Vendor>

    @Transaction
    @Query("SELECT * FROM vendor")
    fun getVendorWithCars(): List<VendorWithCars>

    @Query("SELECT * FROM vendor WHERE vid = :id")
    suspend fun getVendorByID(id: Long): Vendor

    @Query("DELETE FROM vendor WHERE vid = :id")
    suspend fun deleteVendorByID(id: Long)
}