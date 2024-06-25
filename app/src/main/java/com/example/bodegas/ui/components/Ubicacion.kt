package com.example.bodegas.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bodegas.data.models.AsignarUbicacionId
import com.example.bodegas.data.models.UbicacionResponse
import com.example.bodegas.data.repository.DataRepository
import com.example.bodegas.data.repository.IpRepository
import com.example.bodegas.utils.Global
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsignarUbicacion(navController: NavHostController) {
    var piso by remember { mutableStateOf("") }
    var ubicaciones by remember { mutableStateOf(listOf<UbicacionResponse>()) }
    var selectedService by remember { mutableStateOf<UbicacionResponse?>(null) } // Store the whole object
    var expanded by remember { mutableStateOf(false) }

    val dataRepository = remember { DataRepository() }
    val ipRepository = remember { IpRepository() }
    val coroutineScope = rememberCoroutineScope()

    var selectedIdUbicacion by remember { mutableStateOf(0) } // Variable para el IdUbicacion


    var isLoading by remember { mutableStateOf(false) } // Track loading state
    var error by remember { mutableStateOf<String?>(null) } // Store error message
    val idEquipo by remember { mutableStateOf(Global.IDEquipo?.toInt() ?: 0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField( // Use OutlinedTextField for better visuals
            value = piso,
            onValueChange = { piso = it },
            label = { Text("Ingrese el piso") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    isLoading = true // Start loading
                    try {
                        val response = dataRepository.getUbicacionesPorPiso(piso)
                        if (response.isSuccessful) {
                            ubicaciones = response.body() ?: listOf()
                            selectedService = null // Reset selection when getting new data
                        } else {
                            error = "Error al obtener ubicaciones"
                        }
                    } catch (e: Exception) {
                        error = "Error de conexión"
                    } finally {
                        isLoading = false // Stop loading
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading // Disable while loading
        ) {
            Text(if (isLoading) "Cargando..." else "Obtener Ubicaciones")
        }

        // Display error message if present
        error?.let {
            Text(it, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown for service selection
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedService?.Servicio ?: "",
                onValueChange = {},
                readOnly = true,
                label = { Text("Seleccionar Servicio") }, // Etiqueta para el dropdown
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                ubicaciones.forEach { ubicacion ->
                    DropdownMenuItem(
                        text = { Text(ubicacion.Servicio) },
                        onClick = {
                            selectedService = ubicacion // Actualiza el servicio seleccionado
                            selectedIdUbicacion = ubicacion.IdUbicacion // Guarda el IdUbicacion
                            expanded = false
                        }
                    )
                }
            }
        }


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = {
                coroutineScope.launch {
                    try {
                        if (selectedService == null) {
                            return@launch
                        }
                        val asignacion = AsignarUbicacionId(IDUbicacion = selectedIdUbicacion)
                        val response = ipRepository.asignarUbicacion(
                            idEquipo.toString(),
                            asignacion
                        )
                        if (response.isSuccessful) {
                            Log.d("AsignarUbicacion", "Asignacion exitosa")
                            navController.navigate("home")
                        }

                    } catch (e: Exception) {
                        Log.e("AsignarUbicacion", "Error al asignar ubicacion", e)
                    }

                }
            },
            enabled = selectedService != null
        ) {
            Text("Asignar Ubicacion")
        }
    }
}
