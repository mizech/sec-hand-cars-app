package com.example.secondhandcars.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.secondhandcars.models.Vendor
import com.example.secondhandcars.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun VendorForm(viewModel: MainViewModel,
               navController: NavController,
               vId: Long) {
    var cScope = rememberCoroutineScope()

    var currName = remember {
        mutableStateOf("")
    }

    var currCountry = remember {
        mutableStateOf("")
    }

    if (vId != 0L) {
        LaunchedEffect(key1 = true) {
            val vendor = viewModel.getVendorByID(id = vId)
            currName.value = vendor.name
            currCountry.value = vendor.country
        }
    }

    Scaffold { innerPadding ->
        Column(modifier = Modifier.fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(vertical = innerPadding.calculateTopPadding()),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "${if (vId == 0L) "Create" else "Edit" } vendor",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 16.dp, top = 80.dp),
                textAlign = TextAlign.Start)
            OutlinedTextField(value = currName.value, onValueChange = {
                currName.value = it
            }, modifier = Modifier.padding(bottom = 25.dp),
                placeholder = {
                    Text("Insert vendor's name")
                })
            OutlinedTextField(value = currCountry.value, onValueChange = {
                currCountry.value = it
            }, modifier = Modifier.padding(bottom = 25.dp),
                placeholder = {
                    Text("Insert vendor's country")
                })
            OutlinedButton(onClick = {
                if (currName.value.isEmpty() || currCountry.value.isEmpty()) {
                    return@OutlinedButton
                }

                cScope.launch {
                    viewModel.insert(newVendor = Vendor(name = currName.value,
                        country = currCountry.value))
                    navController.navigate(Routes.VendorsList.name)
                }
            }) {
                Text(text = "Submit")
            }
        }
    }
}