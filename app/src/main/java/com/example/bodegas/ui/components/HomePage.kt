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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bodegas.utils.Global

@Composable
fun HomePage(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = { navController.navigate("buscarip") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = ButtonDefaults.elevatedButtonElevation()
        ) {
            Text(text = "Registro equipo", color = Color.White)
        }
        Button(
            onClick = { navController.navigate("impresora") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = ButtonDefaults.elevatedButtonElevation()
        ) {
            Text(text = "Registro impresora", color = Color.White)
        }
        Button(
            onClick = { navController.navigate("usuario") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = ButtonDefaults.elevatedButtonElevation()
        ) {
            Text(text = "Registro usuario", color = Color.White)
        }
        Button(
            onClick = { navController.navigate("buscar") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = ButtonDefaults.elevatedButtonElevation()
        ) {
            Text(text = "Buscar equipo", color = Color.White)
        }

        Button(
            onClick = {
                Global.token = null // Borra el token global
                navController.navigate("login")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = ButtonDefaults.elevatedButtonElevation()
        ) {
            Text(text = "Cerrar sesion", color = Color.White)
        }
    }
}