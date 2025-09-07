package com.example.secondhandcars.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.secondhandcars.ui.theme.SecondHandCarsTheme

@Composable
fun BackButton(navController: NavController) {
    Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Canvas(modifier = Modifier.size(100.dp), onDraw = {
                drawCircle(color = Color(red = 0f, green = 0f, blue = 0f, alpha = 0.1f), radius = 100f)
            })
            Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "", modifier = Modifier.size(38.dp))
        }
    }
}
