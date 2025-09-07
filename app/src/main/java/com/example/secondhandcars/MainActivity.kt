package com.example.secondhandcars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.secondhandcars.models.DatabaseClient
import com.example.secondhandcars.ui.theme.SecondHandCarsTheme
import com.example.secondhandcars.viewmodels.MainViewModel
import com.example.secondhandcars.views.NavView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val database = DatabaseClient(context = applicationContext)
        val vendorDao = database.instance.vendorDao()
        var carDao = database.instance.carDao()

        val viewModel by viewModels<MainViewModel>(
            factoryProducer = {
                object: ViewModelProvider.Factory {
                    override fun <T: ViewModel> create(modelClass: Class<T>): T {
                        return MainViewModel(vendorDao = vendorDao, carDao = carDao) as T
                    }
                }
            }
        )

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SecondHandCarsTheme {
                NavView(viewModel = viewModel)
            }
        }
    }
}
