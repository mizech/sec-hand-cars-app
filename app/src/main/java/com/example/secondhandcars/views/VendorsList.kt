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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.secondhandcars.models.Vendor
import com.example.secondhandcars.viewmodels.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VendorsList(viewModel: MainViewModel,
                navController: NavController) {
    val vendorsList = remember {
        mutableStateListOf<Vendor>()
    }

    LaunchedEffect(key1 = true) {
        vendorsList.addAll(viewModel.getAllVendors())
    }
    Box {
        Scaffold { innerPadding ->
            Column(verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .padding(top = 100.dp)) {
                Text(text = "Available vendors",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textAlign = TextAlign.Start)
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Name",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 16.dp))
                    Text(text = "Country",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                }
                LazyColumn {
                    items(items = vendorsList) {
                        Card(modifier = Modifier.padding(bottom = 12.dp).clickable {
                            navController.navigate(
                                Routes.VendorDetails.append(arg = it.vid.toString()))
                        },
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
                            Row(horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                                    .padding(vertical = 12.dp)
                                    .padding(horizontal = 10.dp)) {
                                Text(text = it.name,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier)
                                Text(text = it.country,
                                    modifier = Modifier)
                            }
                        }
                    }
                }
            }
        }
        AddEntityButton(navController = navController) {
            Routes.VendorForm.append(arg = "")
        }
    }
}