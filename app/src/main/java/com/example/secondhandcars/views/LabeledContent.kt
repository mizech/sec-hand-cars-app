package com.example.secondhandcars.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LabeledContent(label: String, value: String) {
    Card(modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp)) {
        Column(modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(vertical = 12.dp)) {
            Text(text = label, fontSize = 20.sp)
            Text(text = value, fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier)
        }
    }
}
