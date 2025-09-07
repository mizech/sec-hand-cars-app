package com.example.secondhandcars.viewmodels

import androidx.lifecycle.ViewModel
import com.example.secondhandcars.models.Car
import com.example.secondhandcars.models.CarDao
import com.example.secondhandcars.models.Vendor
import com.example.secondhandcars.models.VendorDao

class MainViewModel(val vendorDao: VendorDao, val carDao: CarDao): ViewModel() {
    suspend fun getAllVendors(): List<Vendor> {
        return vendorDao.getAllVendors()
    }

    suspend fun getAllCars(): List<Car> {
        return carDao.getAllCars()
    }

    suspend fun getCarByID(id: Long): Car {
        return carDao.getCarByID(id = id)
    }

    suspend fun getVendorByID(id: Long): Vendor {
        return vendorDao.getVendorByID(id = id)
    }

    suspend fun insert(newCar: Car) {
        carDao.insert(car = newCar)
    }

    suspend fun insert(newVendor: Vendor) {
        vendorDao.insert(vendor = newVendor)
    }

    suspend fun update(car: Car) {
        carDao.update(name = car.name,
                        price = car.price,
                        vid = car.vid,
                        cid = car.cid)
    }

    suspend fun deleteCarById(id: Long) {
        carDao.deleteCarByID(id = id)
    }

    suspend fun deleteVendorById(id: Long) {
        vendorDao.deleteVendorByID(id = id)
    }
}