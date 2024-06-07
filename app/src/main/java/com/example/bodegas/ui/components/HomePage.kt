package com.example.bodegas.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomePage(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = {
                navController.navigate("equipo")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Equipo")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = {
                navController.navigate("hardware")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Hardware")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = {
                navController.navigate("software")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Software")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = {
                navController.navigate("impresora")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Impresora")
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}