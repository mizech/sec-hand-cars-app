package com.example.secondhandcars.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.secondhandcars.viewmodels.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun NavView(viewModel: MainViewModel) {
    val navController = rememberNavController()

    var isActionMenuShown = remember {
        mutableStateOf(false)
    }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(actions = {
            IconButton(onClick = {
                isActionMenuShown.value = !isActionMenuShown.value
            }) {
                Icon(Icons.Default.Menu,
                    contentDescription = "")
            }
            DropdownMenu(expanded = isActionMenuShown.value,
                onDismissRequest = {
                    isActionMenuShown.value = false
                }) {
                DropdownMenuItem(text = {
                    Text(text = "Cars list")
                }, onClick = {
                    navController.navigate(route = Routes.CarsList.name)
                })
                DropdownMenuItem(text = {
                    Text(text = "Vendors list")
                }, onClick = {
                    navController.navigate(route = Routes.VendorsList.name)
                })
                if (viewModel.countOfVendors > 0) {
                    println("Count if: ${viewModel.countOfVendors}")
                    DropdownMenuItem(text = {
                        Text(text = "Create car")
                    }, onClick = {
                        navController.navigate(route = Routes.CarForm.append(arg = ""))
                    })
                } else {
                    println("Count else: ${viewModel.countOfVendors}")
                }
                DropdownMenuItem(text = {
                    Text(text = "Create vendor")
                }, onClick = {
                    navController.navigate(route = Routes.VendorForm.name)
                })
            }
        }, title = {
            Text(text = "Second-hand cars", fontSize = 24.sp)
        })
    }) { innerPadding ->
        NavHost(navController = navController,
            startDestination = Routes.CarsList.name) {
            composable(route = Routes.CarForm.name) { entry ->
                val cId = entry.arguments?.getString("cId") ?: ""
                CarForm(viewModel = viewModel,
                    navController = navController,
                    cId = cId)
            }
            composable(route = Routes.CarsList.name) {
                CarList(viewModel = viewModel,
                        navController = navController)
            }
            composable(route = Routes.VendorsList.name) {
                VendorsList(viewModel = viewModel,
                    navController = navController)
            }
            composable(route = Routes.VendorForm.name) { entry ->
                val sVid = entry.arguments?.getString("vId") ?: ""
                var lVid: Long = 0
                try {
                    lVid = sVid.toLong()
                } catch (exception: Exception) {
                    print(exception.message)
                }
                VendorForm(viewModel = viewModel,
                            navController = navController,
                            vId = lVid)
            }
            composable(route = Routes.CarDetails.name) { entry ->
                val cId = entry.arguments?.getString("cId")
                cId?.let { cId ->
                    CarDetails(viewModel = viewModel,
                        navController = navController,
                        cId = cId,
                        innerPadding = innerPadding)
                }
            }
            composable(route = Routes.VendorDetails.name) { entry ->
                val vId = entry.arguments?.getString("vId")
                vId?.let { vId ->
                    VendorDetails(viewModel = viewModel,
                                navController = navController,
                                vId = vId,
                                innerPadding = innerPadding)
                }
            }
        }
    }
}