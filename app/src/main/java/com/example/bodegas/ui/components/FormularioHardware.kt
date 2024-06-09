package com.example.bodegas.ui.components

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.bodegas.data.models.Hardware
import com.example.bodegas.data.repository.DataRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FormularioHardware(
    navController: NavHostController,
    equipoId: String?
) {
    var equipoIdState by remember { mutableStateOf(equipoId ?: "") }

    var NumeroInventario by remember { mutableStateOf("") }
    var MarcaEquipo by remember { mutableStateOf("") }
    var ModeloEquipo by remember { mutableStateOf("") }
    var MarcaPlaca by remember { mutableStateOf("") }
    var ModeloPlaca by remember { mutableStateOf("") }
    var Procesador by remember { mutableStateOf("") }
    var Ram by remember { mutableStateOf("") }
    var TarjetaVideo by remember { mutableStateOf("") }
    var Almacenamiento by remember { mutableStateOf("") }
    var MarcaImpresora by remember { mutableStateOf("") }
    var ModeloImpresora by remember { mutableStateOf("") }
    var ProveedorImpresora by remember { mutableStateOf("") }
    var Huellero by remember { mutableStateOf(false) }
    var Scanner by remember { mutableStateOf(false) }
    var SerieScanner by remember { mutableStateOf("") }
    var FirmaElectronica by remember { mutableStateOf(false) }
    var LectorCodigoBarras by remember { mutableStateOf(false) }
    var NumeroInventarioCodigoBarras by remember { mutableStateOf("") }

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
            text = "Registro de hardware",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = NumeroInventario,
            onValueChange = { NumeroInventario = it },
            label = { Text("Número de inventario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = MarcaEquipo,
            onValueChange = { MarcaEquipo = it },
            label = { Text("Marca del equipo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = ModeloEquipo,
            onValueChange = { ModeloEquipo = it },
            label = { Text("Modelo del equipo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = MarcaPlaca,
            onValueChange = { MarcaPlaca = it },
            label = { Text("Marca de la placa") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = ModeloPlaca,
            onValueChange = { ModeloPlaca = it },
            label = { Text("Modelo de la placa") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = Procesador,
            onValueChange = { Procesador = it },
            label = { Text("Procesador") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = Ram.toString(),
            onValueChange = { Ram = it },
            label = { Text("RAM") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = TarjetaVideo,
            onValueChange = { TarjetaVideo = it },
            label = { Text("Tarjeta de video") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = Almacenamiento,
            onValueChange = { Almacenamiento = it },
            label = { Text("Almacenamiento") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = MarcaImpresora,
            onValueChange = { MarcaImpresora = it },
            label = { Text("Marca de la impresora") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = ModeloImpresora,
            onValueChange = { ModeloImpresora = it },
            label = { Text("Modelo de la impresora") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = ProveedorImpresora,
            onValueChange = { ProveedorImpresora = it },
            label = { Text("Proveedor de la impresora") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "Huellero disponible ?")
        Switch(

            checked = Huellero,
            onCheckedChange = { Huellero = it }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "Scanner disponible ?")
        Switch(
            checked = Scanner,
            onCheckedChange = { Scanner = it }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = SerieScanner,
            onValueChange = { SerieScanner = it },
            label = { Text("Serie del scanner") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "Firma electrónica disponible ?")
        Switch(
            checked = FirmaElectronica,
            onCheckedChange = { FirmaElectronica = it }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "Lector de código de barras disponible ?")
        Switch(
            checked = LectorCodigoBarras,
            onCheckedChange = { LectorCodigoBarras = it }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = NumeroInventarioCodigoBarras,
            onValueChange = { NumeroInventarioCodigoBarras = it },
            label = { Text("Código de barras") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = {
                if (NumeroInventario.isNotEmpty()) {
                    val hardware = Hardware(
                        IDEquipo = equipoIdState.toIntOrNull() ?: 0,
                        NumeroInventario,
                        MarcaEquipo,
                        ModeloEquipo,
                        MarcaPlaca,
                        ModeloPlaca,
                        Procesador,
                        Ram,
                        TarjetaVideo,
                        Almacenamiento,
                        MarcaImpresora,
                        ModeloImpresora,
                        ProveedorImpresora,
                        Huellero,
                        Scanner,
                        SerieScanner,
                        FirmaElectronica,
                        LectorCodigoBarras,
                        NumeroInventarioCodigoBarras
                    )
                    // Llamar a la función de crear hardware
                    scope.launch {
                        try {
                            val response = repository.crearHardware(hardware)
                            if (response.isSuccessful) {
                                showSnackbar = true
                                delay(2000)
                                showSnackbar = false
                                //petición exitosa
                                navController.navigate("software/$equipoIdState")
                            } else {
                                Log.e("Error", response.errorBody().toString())
                            }
                        } catch (e: Exception) {
                            Log.e("Error", e.message.toString())

                        }
                    }
                } else {
                    showDialog = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Registrar")
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