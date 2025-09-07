package com.example.secondhandcars.views

import android.annotation.SuppressLint
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.secondhandcars.models.Vendor
import com.example.secondhandcars.viewmodels.MainViewModel
import kotlinx.coroutines.launch
import kotlin.getValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VendorDetails(
    viewModel: MainViewModel,
    vId: String = "",
    navController: NavController,
    innerPadding: PaddingValues
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = innerPadding.calculateTopPadding())
            .padding(horizontal = 25.dp)
            .padding(bottom = 6.dp)
    ) {
        val cscope = rememberCoroutineScope()

        var vendor = remember {
            mutableStateOf(Vendor(vid = 0, name = "", country = ""))
        }
        var isDeleteActive = remember {
            mutableStateOf(false)
        }
        LaunchedEffect(key1 = true) {
            vendor.value = viewModel.getVendorByID(id = vId.toLong())
        }

        if (isDeleteActive.value == true) {
            BasicAlertDialog(onDismissRequest = {
                isDeleteActive.value = false
            }) {
                Surface(shape = RoundedCornerShape(size = 12.dp)) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Vendor and all related cars become deleted.",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Are you sure?",
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.padding(bottom = 12.dp))
                        OutlinedButton(onClick = {
                            cscope.launch {
                                viewModel.deleteVendorById(id = vendor.value.vid)
                                navController.navigate(Routes.VendorsList.name)
                            }
                        }, modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Delete",
                                color = Color.Red,
                                fontWeight = FontWeight.Bold
                            )
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

        Text(
            text = "Vendor details",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )
        LabeledContent(label = "Name", value = vendor.value.name)
        LabeledContent(label = "Country", value = vendor.value.country)
        Row {
            Button(onClick = {
                isDeleteActive.value = true
            }, modifier = Modifier.weight(2f)) {
                Text(
                    text = "Delete",
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                navController.navigate(Routes.VendorForm.append(arg = vId))
            }, modifier = Modifier.weight(2f)) {
                Text(
                    text = "Edit",
                    fontSize = 24.sp
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        BackButton(navController = navController)
    }
}


