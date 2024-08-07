package com.example.bodegas.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bodegas.data.repository.DataRepository
import kotlinx.coroutines.launch


@Composable
fun BuscarEquipo(navController: NavHostController, repository: DataRepository) {
    var macAddress by remember { mutableStateOf("") }
    var equipoId by remember { mutableStateOf("") }
    var faltaEnComponenteHardware by remember { mutableStateOf(false) }
    var faltaEnSoftwareInstalado by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()


    // Declaración de las variables para habilitar los botones
    val habilitarEditarEquipo = faltaEnComponenteHardware && faltaEnSoftwareInstalado
    val habilitarRegistroHardware = faltaEnComponenteHardware && !faltaEnSoftwareInstalado
    val habilitarRegistroSoftware = !faltaEnComponenteHardware && faltaEnSoftwareInstalado

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = macAddress,
            onValueChange = { macAddress = it },
            label = { Text("Ingrese la dirección IP") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                Log.d("habilitarEditarEquipo", habilitarEditarEquipo.toString())
                Log.d("habilitarRegistroHardware", habilitarRegistroHardware.toString())
                Log.d("habilitarRegistroSoftware", habilitarRegistroSoftware.toString())
                coroutineScope.launch {
                    try {
                        val response = repository.getEquipoPorMac(macAddress)
                        if (response.isSuccessful) {
                            val equipo = response.body()
                            equipoId = equipo?.IDEquipo.toString()
                            faltaEnComponenteHardware = equipo?.FaltaEnComponenteHardware ?: false
                            faltaEnSoftwareInstalado = equipo?.FaltaEnSoftwareInstalado ?: false

                        } else {
                            // Manejar el caso en que la respuesta no sea exitosa
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
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("home")
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Atras")
        }
        Text("ID del equipo: $equipoId")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("hardware/$equipoId") },
            enabled = habilitarRegistroHardware,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Retomar desde hardware")
        }
        Button(
            onClick = { navController.navigate("software/$equipoId") },
            enabled = habilitarRegistroSoftware,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Retomar desde software")
        }
        Button(
            onClick = {
                navController.navigate("editarEquipo/$equipoId")
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = habilitarEditarEquipo
        ) {
            Text("Retomar desde equipo")
        }
    }
}