package com.example.secondhandcars.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secondhandcars.models.Car
import com.example.secondhandcars.models.CarDao
import com.example.secondhandcars.models.Vendor
import com.example.secondhandcars.models.VendorDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val vendorDao: VendorDao, val carDao: CarDao): ViewModel() {
    var countOfVendors = 0
        private set

    init {
        updateCountOfVendors()
    }

    fun updateCountOfVendors() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                countOfVendors = getCountOfVendors()
            }
        }
    }

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
        updateCountOfVendors()
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
        updateCountOfVendors()
    }

    suspend fun getCountOfVendors(): Int {
        return vendorDao.getCountOfVendors()
    }
}