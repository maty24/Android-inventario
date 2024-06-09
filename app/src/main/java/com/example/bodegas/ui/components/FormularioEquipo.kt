package com.example.bodegas.ui.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bodegas.data.models.Equipo
import com.example.bodegas.data.repository.DataRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun FormularioEquipo(
    navController: NavController
) {
    var MascaraRed by remember { mutableStateOf("") }
    var PuertaEnlace by remember { mutableStateOf("") }
    var DnsPrimario by remember { mutableStateOf("") }
    var DnsSecundario by remember { mutableStateOf("") }
    var MacAddress by remember { mutableStateOf("") }
    var MiniSwitch by remember { mutableStateOf("") }
    var IpSwitch by remember { mutableStateOf("") }
    var PuertoSwitch by remember { mutableStateOf("") }
    var NombreEquipo by remember { mutableStateOf("") }
    var NombreUsuarioPC by remember { mutableStateOf("") }
    var Dominio by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }
    var showSnackbar by remember { mutableStateOf(false) }

    val repository = remember { DataRepository() } // Crear el repositorio
    val scope = rememberCoroutineScope() // Crear un CoroutineScope


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Registro de equipos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = MascaraRed,
            onValueChange = { MascaraRed = it.take(15) },
            label = { Text("Máscara de Red") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = PuertaEnlace,
            onValueChange = { PuertaEnlace = it },
            label = { Text("Puerta de Enlace") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = DnsPrimario,
            onValueChange = { DnsPrimario = it },
            label = { Text("DNS Primario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = DnsSecundario,
            onValueChange = { DnsSecundario = it },
            label = { Text("DNS Secundario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = MacAddress,
            onValueChange = { MacAddress = it },
            label = { Text("MAC Address") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = MiniSwitch,
            onValueChange = { MiniSwitch = it },
            label = { Text("Mini Switch") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = IpSwitch,
            onValueChange = { IpSwitch = it },
            label = { Text("IP Switch") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = PuertoSwitch,
            onValueChange = { PuertoSwitch = it },
            label = { Text("Puerto Switch") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = NombreEquipo,
            onValueChange = { NombreEquipo = it },
            label = { Text("Nombre del Equipo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = NombreUsuarioPC,
            onValueChange = { NombreUsuarioPC = it },
            label = { Text("Nombre de Usuario de PC") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = Dominio,
            onValueChange = { Dominio = it },
            label = { Text("Dominio") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                // Validación
                if (NombreEquipo.isNotBlank()) {

                    val equipo = Equipo(
                        MascaraRed, PuertaEnlace, DnsPrimario, DnsSecundario,
                        MacAddress, MiniSwitch, IpSwitch, PuertoSwitch,
                        NombreEquipo, NombreUsuarioPC, Dominio
                    )

                    // Lanzar la corrutina para enviar los datos
                    scope.launch {
                        try {
                            val response = repository.crearEquipo(equipo)
                            if (response.isSuccessful) { // <-- Ahora puedes usar isSuccessful
                                showSnackbar = true // Mostrar el Snackbar
                                delay(2000) // Esperar 2 segundos
                                showSnackbar = false // Ocultar el Snackbar
                                val equipoId = response.body()?.IDEquipo.toString()
                                // La petición fue exitosa
                                navController.navigate("hardware/$equipoId")
                            } else {
                                Log.e(
                                    "API_ERROR",
                                    "Error code: ${response.code()}, Error body: ${
                                        response.errorBody()?.string()
                                    }"
                                )
                            }
                        } catch (e: Exception) {
                            // Manejar la excepción (por ejemplo, problemas de red)
                            Log.e("API_EXCEPTION", "Exception: ", e)
                        }
                    }
                } else {
                    showDialog = true // Mostrar el diálogo si el nombre está vacío
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear Equipo")
        }
        Spacer(modifier = Modifier.height(4.dp))
        if (showSnackbar) {
            Snackbar(
                action = {
                    TextButton(onClick = { showSnackbar = false }) {
                        Text("OK")
                    }
                }
            ) {
                Text("Equipo creado con éxito")
            }
        }
        Button(
            onClick = {
                navController.navigate("home")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Atras")
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false }, // Cerrar el diálogo al presionar fuera
                title = { Text("Error") },
                text = { Text("Por favor, ingresa el nombre del equipo.") },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Aceptar")
                    }
                }
            )
        }
    }


}