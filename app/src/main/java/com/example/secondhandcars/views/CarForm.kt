package com.example.secondhandcars.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.secondhandcars.viewmodels.MainViewModel
import com.example.secondhandcars.R
import com.example.secondhandcars.models.Car
import com.example.secondhandcars.models.Vendor
import kotlinx.coroutines.launch

@Composable
fun CarForm(viewModel: MainViewModel,
            navController: NavController,
            cId: String = "") {
    val isDropDownExpanded = remember {
        mutableStateOf(false)
    }

    var givenName = remember {
        mutableStateOf("")
    }

    var givenPrice = remember {
        mutableStateOf("")
    }

    val selectedVendor = remember {
        mutableStateOf(Vendor(name = "", country = ""))
    }

    var vendors = remember {
        mutableListOf<Vendor>(Vendor(name = "", country = ""))
    }

    var car = remember {
        mutableStateOf(Car(name = "", price = 0.0, vid = 0))
    }

    var isExistingCar = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        vendors.addAll(viewModel.getAllVendors())
        if (cId != "") {
            car.value = viewModel.getCarByID(id = cId.toLong())
            givenName.value = car.value.name
            givenPrice.value = car.value.price.toString()

            val vendor = vendors.find { it.vid == car.value.vid }
            vendor?.let {
                selectedVendor.value = it
            }

            isExistingCar.value = true
        }
    }

    val rcScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = givenName.value, onValueChange = {
            givenName.value = it
        }, placeholder = {
            Text(text = "Insert car's name")
        }, modifier = Modifier.padding(bottom = 25.dp))
        OutlinedTextField(value = givenPrice.value, onValueChange = { newVal: String ->
            givenPrice.value = newVal
        }, placeholder = {
            Text(text = "Insert price")
        }, modifier = Modifier.padding(bottom = 25.dp),
            trailingIcon = {
                Icon(Icons.Default.Clear, contentDescription = "",
                    modifier = Modifier.clickable {
                        givenPrice.value = ""
                    })
            }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Box {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    isDropDownExpanded.value = true
                }.padding(bottom = 25.dp)
            ) {
                Text(text = selectedVendor.value.name)
                Image(
                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                    contentDescription = "DropDown Icon"
                )
            }
            DropdownMenu(
                expanded = isDropDownExpanded.value,
                onDismissRequest = {
                    isDropDownExpanded.value = false
                }) {
                vendors.forEachIndexed { index, vendor ->
                    DropdownMenuItem(text = {
                        Text(text = vendor.name)
                    },
                        onClick = {
                            isDropDownExpanded.value = false
                            selectedVendor.value = vendor
                        })
                }
            }
        }
        OutlinedButton(onClick = {
            if (isExistingCar.value == false) {
                car.value.name = givenName.value
                car.value.price = givenPrice.value.toDoubleOrNull() ?: 0.0
                car.value.vid = selectedVendor.value.vid
                rcScope.launch {
                    viewModel.insert(newCar = car.value)
                    navController.navigate(Routes.CarsList.name)
                }
            } else {
                rcScope.launch {
                    car.value.name = givenName.value
                    car.value.price = givenPrice.value.toDoubleOrNull() ?: 0.0
                    car.value.vid = selectedVendor.value.vid
                    viewModel.update(car = car.value)
                    navController.navigate(Routes.CarsList.name)
                }
            }
        }) {
            Text(text = "Submit")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}