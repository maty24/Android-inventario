package com.example.bodegas.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bodegas.data.repository.IpRepository
import kotlinx.coroutines.launch


@Composable
fun BuscarIpDisponible(navController: NavHostController, repository: IpRepository) {

    var ipDisponible by remember { mutableStateOf("") }
    var ipId by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = ipDisponible,
            onValueChange = { ipDisponible = it },
            label = { Text("Ingrese la dirección IP") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                coroutineScope.launch {
                    try {
                        val response = repository.verificarIp(ipDisponible)
                        if (response.code() == 404) {
                            showDialog = true
                        }
                        if (response.isSuccessful) {
                            val ipBody = response.body()
                            ipId = ipBody?.id.toString()
                            Log.d("IP_DISPONIBLE", "IP Disponible: $ipId")
                            navController.navigate("equipo/$ipId")

                        }
                    } catch (e: Exception) {
                        // Manejar la excepción (por ejemplo, problemas de red)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar")
        }
        Button(onClick = {
            navController.navigate("home")
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Atras")
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false }, // Cerrar el diálogo al presionar fuera
                title = { Text("Error") },
                text = { Text("No existe esa ip o no esta disponible ") },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Aceptar")
                    }
                }
            )
        }
    }
}