package com.example.bodegas.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bodegas.data.models.Impresora
import com.example.bodegas.data.repository.DataRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FormularioImpresora(navController: NavHostController) {

    var NumeroSerie by remember { mutableStateOf("") }
    var Marca by remember { mutableStateOf("") }
    var Modelo by remember { mutableStateOf("") }
    var TipoInterface by remember { mutableIntStateOf(0) }
    var TipoUso by remember { mutableStateOf("") }

    var ipImpresora by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }
    var showSnackbar by remember { mutableStateOf(false) }

    val repository = remember { DataRepository() } // Crear el repositorio
    val scope = rememberCoroutineScope() // Crear un CoroutineScope

    val options = listOf("USB", "LAN")
    var selectedOption by remember { mutableStateOf(options[0]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Registro de impresoras",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = NumeroSerie,
            onValueChange = { NumeroSerie = it },
            label = { Text("Número de serie") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = Marca,
            onValueChange = { Marca = it },
            label = { Text("Marca") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = Modelo,
            onValueChange = { Modelo = it },
            label = { Text("Modelo") },
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Tipo de red impresora", style = MaterialTheme.typography.headlineLarge)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { text ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    RadioButton(
                        selected = text == selectedOption,
                        onClick = { selectedOption = text },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.primary,
                            unselectedColor = MaterialTheme.colorScheme.onSurface,
                            disabledSelectedColor = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier.padding(end = 16.dp)
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodySmall,
                        color = if (text == selectedOption) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
        if (selectedOption == "USB") {
            TipoInterface = 1
        } else if (selectedOption == "LAN") {
            TipoInterface = 2
        }
        Spacer(modifier = Modifier.height(4.dp))
        if (selectedOption == "USB") {
            TipoInterface = 1
            ipImpresora = ""
        } else if (selectedOption == "LAN") {
            TipoInterface = 2
        }
        OutlinedTextField(
            value = ipImpresora,
            onValueChange = { ipImpresora = it },
            label = { Text("IP impresora") },
            enabled = selectedOption == "LAN",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = TipoUso,
            onValueChange = { TipoUso = it },
            label = { Text("Tipo de uso") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = {
                val impresora = Impresora(
                    NumeroSerie,
                    Marca,
                    Modelo,
                    TipoInterface,
                    TipoUso
                )
                scope.launch {
                    val response = repository.crearImpresora(impresora)
                    if (response.isSuccessful) {
                        showSnackbar = true // Mostrar el Snackbar
                        delay(2000) // Esperar 2 segundos
                        showSnackbar = false // Ocultar el Snackbar
                        navController.navigate("home")
                    } else {
                        Log.e(
                            "API_ERROR",
                            "Error code: ${response.code()}, Error body: ${
                                response.errorBody()?.string()
                            }"
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar impresora")
        }
        Button(
            onClick = {
                navController.navigate("home")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Atras")
        }
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

