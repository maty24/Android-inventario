package com.example.bodegas.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.bodegas.data.models.Software
import com.example.bodegas.data.models.edicionOffice
import com.example.bodegas.data.models.sistemasOperativos
import com.example.bodegas.data.models.versionOffice
import com.example.bodegas.data.models.versionesSistemasOperativos
import com.example.bodegas.data.repository.DataRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioSoftware(
    navController: NavHostController,
    equipoId: String?
) {
    var equipoIdState by remember { mutableStateOf(equipoId ?: "") }

    val optionsSistemaOperativo = sistemasOperativos
    var edicionSistemaOperativo by remember { mutableStateOf(optionsSistemaOperativo[0]) }
    var expandedSistemaOperativo by remember { mutableStateOf(false) }

    val optionsVersionSistemaOperativo = versionesSistemasOperativos
    var versionSistemaOperativo by remember { mutableStateOf("") }
    var expandedVersionSistemaOperativo by remember { mutableStateOf(false) }

    val optionEdOffice = edicionOffice
    var edicionOffice by remember { mutableStateOf("") }
    var expandedEdOffice by remember { mutableStateOf(false) }

    val optionVersionOffice = versionOffice
    var versionOffice by remember { mutableStateOf("") }
    var expandedVersionOffice by remember { mutableStateOf(false) }


    var edicionAntivirus by remember { mutableStateOf("") }
    var versionAntivirus by remember { mutableStateOf("") }
    var maquinaVirtual by remember { mutableStateOf("") }
    var versionMaquinaVirtual by remember { mutableStateOf("") }
    var otrosProgramas by remember { mutableStateOf("") }
    var versionOtrosProgramas by remember { mutableStateOf("") }
    var sistemaSoporteRemoto by remember { mutableStateOf(false) }
    var nombreSoporteRemoto by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }
    var showSnackbar by remember { mutableStateOf(false) }

    val repository = remember { DataRepository() } // Crear el repositorio
    val scope = rememberCoroutineScope() // Crear un CoroutineScope


    // Contenido del formulario
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Registro de Software",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "Sistema operativo")
        ExposedDropdownMenuBox(
            expanded = expandedSistemaOperativo,
            onExpandedChange = { expandedSistemaOperativo = !expandedSistemaOperativo }
        ) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = edicionSistemaOperativo,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedSistemaOperativo) }
            )
            ExposedDropdownMenu(
                expanded = expandedSistemaOperativo,
                onDismissRequest = { expandedVersionSistemaOperativo = false }) {
                optionsSistemaOperativo.forEachIndexed { index, text ->
                    DropdownMenuItem(
                        text = { Text(text = text) },
                        onClick = {
                            edicionSistemaOperativo = optionsSistemaOperativo[index]
                            expandedSistemaOperativo = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "Versión del sistema operativo")
        ExposedDropdownMenuBox(
            expanded = expandedVersionSistemaOperativo,
            onExpandedChange = { expandedVersionSistemaOperativo = !expandedVersionSistemaOperativo }
        ) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = versionSistemaOperativo,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedVersionSistemaOperativo) }
            )
            ExposedDropdownMenu(
                expanded = expandedVersionSistemaOperativo,
                onDismissRequest = { expandedVersionSistemaOperativo = false }) {
                optionsVersionSistemaOperativo.forEachIndexed { index, text ->
                    DropdownMenuItem(
                        text = { Text(text = text) },
                        onClick = {
                            versionSistemaOperativo = optionsVersionSistemaOperativo[index]
                            expandedVersionSistemaOperativo = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "Edición de Office")
        ExposedDropdownMenuBox(
            expanded = expandedEdOffice,
            onExpandedChange = { expandedEdOffice = !expandedEdOffice }
        ) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = edicionOffice,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedEdOffice) }
            )
            ExposedDropdownMenu(
                expanded = expandedEdOffice,
                onDismissRequest = { expandedEdOffice = false }) {
                optionEdOffice.forEachIndexed { index, text ->
                    DropdownMenuItem(
                        text = { Text(text = text) },
                        onClick = {
                            edicionOffice = optionEdOffice[index]
                            expandedEdOffice = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "Versión de Office")
        ExposedDropdownMenuBox(
            expanded = expandedVersionOffice,
            onExpandedChange = { expandedVersionOffice = !expandedVersionOffice }
        ) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = versionOffice,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedVersionOffice) }
            )
            ExposedDropdownMenu(
                expanded = expandedVersionOffice,
                onDismissRequest = { expandedVersionOffice = false }) {
                optionVersionOffice.forEachIndexed { index, text ->
                    DropdownMenuItem(
                        text = { Text(text = text) },
                        onClick = {
                            versionOffice = optionVersionOffice[index]
                            expandedVersionOffice = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = edicionAntivirus,
            onValueChange = { edicionAntivirus = it },
            label = { Text("Edición del antivirus") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = versionAntivirus,
            onValueChange = { versionAntivirus = it },
            label = { Text("Versión del antivirus") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = maquinaVirtual,
            onValueChange = { maquinaVirtual = it },
            label = { Text("Máquina virtual") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = versionMaquinaVirtual,
            onValueChange = { versionMaquinaVirtual = it },
            label = { Text("Versión de la máquina virtual") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = otrosProgramas,
            onValueChange = { otrosProgramas = it },
            label = { Text("Otros programas") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = versionOtrosProgramas,
            onValueChange = { versionOtrosProgramas = it },
            label = { Text("Versión de otros programas") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "Soporte remoto",
        )
        Switch(
            checked = sistemaSoporteRemoto,
            onCheckedChange = { sistemaSoporteRemoto = it }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = nombreSoporteRemoto,
            onValueChange = { nombreSoporteRemoto = it },
            label = { Text("Nombre del soporte remoto") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = {
                val software = Software(
                    idEquipo = equipoIdState.toInt(),
                    edicionSistemaOperativo,
                    versionSistemaOperativo,
                    edicionOffice,
                    versionOffice,
                    edicionAntivirus,
                    versionAntivirus,
                    maquinaVirtual,
                    versionMaquinaVirtual,
                    otrosProgramas,
                    versionOtrosProgramas,
                    sistemaSoporteRemoto,
                    nombreSoporteRemoto
                )
                scope.launch {
                    try {
                        val response = repository.crearSoftware(software)
                        if (response.isSuccessful) {
                            showSnackbar = true // Mostrar el Snackbar
                            delay(2000) // Esperar 2 segundos
                            showSnackbar = false // Ocultar el Snackbar
                            navController.navigate("home")
                        } else {
                            Log.e("Error", response.errorBody().toString())
                        }
                    } catch (e: Exception) {
                        Log.e("Error", e.message.toString())

                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Registrar software")
        }
        Button(
            onClick = {
                navController.navigate("home")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Volver al inicio")
        }
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

