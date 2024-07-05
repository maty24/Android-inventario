package com.example.bodegas.ui.components

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.bodegas.data.models.AsignacionesEquipo
import com.example.bodegas.data.models.Usuario
import com.example.bodegas.data.repository.DataRepository
import com.example.bodegas.data.repository.IpRepository
import com.example.bodegas.utils.Global
import kotlinx.coroutines.launch


@Composable
fun FormularioUsuario(navController: NavHostController) {

    BackHandler(enabled = true) {}

    var rut by remember { mutableStateOf("") }
    var dv by remember { mutableStateOf("") }
    var nombreFuncionario by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var anexo by remember { mutableStateOf("") }
    var tipoEquipo by remember { mutableStateOf("") }
    var tipoUso by remember { mutableStateOf("") }

    var IDEquipo by remember { mutableStateOf(Global.IDEquipo?.toInt() ?: 0) }
    var IdUsuario by remember { mutableStateOf(0) }


    var btnAsignarEquipo by remember { mutableStateOf(false) }

    val repository = remember { DataRepository() } // Crear el repositorio
    val repositoryIp = remember { IpRepository() } // Crear el repositorio
    val scope = rememberCoroutineScope() // Crear un CoroutineScope

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Registro de usuario",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = rut,
            onValueChange = {
                rut = it
            },
            label = { Text("Rut") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = nombreFuncionario,
            onValueChange = { nombreFuncionario = it },
            label = { Text("Nombre del funcionario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = anexo,
            onValueChange = { anexo = it },
            label = { Text("Anexo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = tipoEquipo,
            onValueChange = { tipoEquipo = it },
            label = { Text("Tipo de equipo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = tipoUso,
            onValueChange = { tipoUso = it },
            label = { Text("Tipo de uso") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (rut.isNotEmpty()) {
                    dv = rut.last().toString()
                    rut = rut.dropLast(1)
                }
                val usuario = Usuario(
                    Rut = rut,
                    Dv = dv,
                    NombreFuncionario = nombreFuncionario,
                    Correo = correo,
                    Anexo = anexo,
                    TipoEquipo = tipoEquipo,
                    TipoUso = tipoUso
                )
                scope.launch {
                    try {
                        val response = repository.crearUsuario(usuario)
                        if (response.isSuccessful) {
                            IdUsuario = response.body()?.IdUsuario ?: 0
                            btnAsignarEquipo = true
                        } else {
                            Log.e(
                                "FormularioUsuario",
                                "Error al crear usuario: ${response.errorBody()}"
                            )
                        }
                    } catch (e: Exception) {
                        Log.e("FormularioUsuario", e.toString())
                    }
                }
            }, Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
        Button(
            onClick = {
                val asignaciones = AsignacionesEquipo(
                    IdEquipo = IDEquipo,
                    IdUsuario = IdUsuario
                )
                scope.launch {
                    val response = repositoryIp.asignarEquipo(asignaciones)
                    if (response.isSuccessful) {
                        Log.d("FormularioUsuario", "Equipo asignado correctamente")
                        navController.navigate("asignarUbicacion") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    } else {
                        Log.e(
                            "FormularioUsuario",
                            "Error al asignar equipo: ${response.errorBody()}"
                        )
                    }
                }
            },
            Modifier.fillMaxWidth(),
            enabled = btnAsignarEquipo
        ) {
            Text(text = "Asignar equipo")
        }
    }
}

private fun formatRut(input: String): String {
    val cleanedInput = input.replace(Regex("[^0-9kK-]"), "") // Permitir números, 'k', 'K' y '-'

    // Si el input ya contiene un '-', simplemente devolverlo
    if (cleanedInput.contains("-")) {
        return cleanedInput
    }

    // Si el input no contiene un '-', agregarlo en la posición correcta
    return when (cleanedInput.length) {
        in 0..1 -> cleanedInput
        else -> cleanedInput.dropLast(1) + "-" + cleanedInput.last()
    }
}