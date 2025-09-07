package com.example.secondhandcars.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddEntityButton(navController: NavController, action: () -> String) {
    Column(horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 30.dp, bottom = 30.dp)) {
        FloatingActionButton(onClick = {
            navController.navigate(action())
        }) {
            Icon(Icons.Filled.Add, "")
        }
    }
}