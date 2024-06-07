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
            Text(text = "Registro equipo")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = {
                navController.navigate("Registro impresora")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Registro impresora")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = {
                navController.navigate("buscar")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Buscar equipo")
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}