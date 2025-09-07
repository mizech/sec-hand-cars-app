package com.example.secondhandcars.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.secondhandcars.models.Car
import com.example.secondhandcars.utils.Formatter
import com.example.secondhandcars.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarDetails(viewModel: MainViewModel,
               cId: String,
               navController: NavController,
               innerPadding: PaddingValues) {
    val coroutineScope = rememberCoroutineScope()
    val formatter = Formatter()
    var car = remember {
        mutableStateOf(Car(cid = 0, name = "", price = 0.0, vid = 0))
    }
    var isDeleteActive = remember {
        mutableStateOf(false)
    }
    fun deleteCar() {
        coroutineScope.launch {
            viewModel.deleteCarById(id = cId.toLong())
            navController.navigate(Routes.CarsList.name)
        }
    }

    LaunchedEffect(key1 = true) {
        car.value = viewModel.getCarByID(id = cId.toLong())
    }

    Column(verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .padding(vertical = innerPadding.calculateTopPadding())
            .padding(horizontal = 25.dp)
            .padding(bottom = 6.dp)) {
        if (isDeleteActive.value == true) {
            BasicAlertDialog(onDismissRequest = {
                isDeleteActive.value = false
            }) {
                Surface(shape = RoundedCornerShape(size = 12.dp)) {
                    Column(modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Car becomes deleted.",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold)
                        Text(text = "Are you sure?",
                            fontSize = 18.sp)
                        Spacer(modifier = Modifier.padding(bottom = 12.dp))
                        OutlinedButton(onClick = {
                            deleteCar()
                        }, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Delete",
                                color = Color.Red,
                                fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.padding(bottom = 12.dp))
                        OutlinedButton(onClick = {
                            isDeleteActive.value = false
                        }, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Cancel")
                        }
                    }
                }
            }
        }
        Text(text = "Car details",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 16.dp),
            textAlign = TextAlign.Start)
        LabeledContent(label = "Name", value = car.value.name)
        LabeledContent(label = "Price",
            value = formatter.formatDouble(value = car.value.price))
        Row {
            Button(onClick = {
                isDeleteActive.value = true
            }, modifier = Modifier.weight(2f)) {
                Text(text = "Delete",
                    fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                navController.navigate(
                    Routes.CarForm.append(
                        arg = car.value.cid.toString()))
            }, modifier = Modifier.weight(2f)) {
                Text(text = "Edit",
                    fontSize = 24.sp)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        BackButton(navController = navController)
    }
}


