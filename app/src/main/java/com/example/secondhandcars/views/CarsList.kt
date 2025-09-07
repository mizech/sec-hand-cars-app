package com.example.secondhandcars.views

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.secondhandcars.models.Car
import com.example.secondhandcars.utils.Formatter
import com.example.secondhandcars.viewmodels.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CarList(viewModel: MainViewModel,
            navController: NavController) {
    val carList = remember {
        mutableStateListOf<Car>()
    }

    val formatter = Formatter()

    LaunchedEffect(key1 = true) {
        carList.addAll(viewModel.getAllCars())
    }

    Box(contentAlignment = Alignment.Center) {
        Scaffold { innerPadding ->
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)
                    .padding(vertical = innerPadding.calculateTopPadding())) {
                Text(text = "Available cars",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 16.dp, top = 80.dp),
                    textAlign = TextAlign.Start)
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Name",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 16.dp))
                    Text(text = "Price",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                }
                LazyColumn {
                    items(carList) {
                        Card(modifier = Modifier.padding(bottom = 12.dp).clickable {
                            navController.navigate(
                                Routes.CarDetails.append(arg = it.cid.toString()))
                        },
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
                            Row(horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                                    .padding(vertical = 12.dp)
                                    .padding(horizontal = 10.dp)) {
                                Text(text = it.name,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier)
                                Text(text = formatter.formatDouble(value = it.price),
                                    modifier = Modifier)
                            }
                        }
                    }
                }
                VerticalDivider()
            }
        }
        AddEntityButton(navController = navController) {
            Routes.CarForm.append(arg = "")
        }
    }
}
