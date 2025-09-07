package com.example.secondhandcars.views

import kotlin.text.replace

sealed class Routes(val name: String) {
    object CarsList: Routes(name = "cars_list")
    object VendorsList: Routes(name = "vendors_list")
    object VendorForm: Routes(name = "vendor_form/{vId}")
    object CarDetails: Routes(name = "car_details/{cId}")
    object VendorDetails: Routes(name = "vendor_details/{vId}")
    object CarForm: Routes(name = "car_form/{cId}")

    fun append(arg: String = ""): String {
        return this.name.replace(regex = "[{].*Id[}]".toRegex(),
            replacement = arg)
    }
}