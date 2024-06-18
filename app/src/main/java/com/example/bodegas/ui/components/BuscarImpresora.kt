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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bodegas.data.models.AsignarImpresora
import com.example.bodegas.data.repository.DataRepository
import com.example.bodegas.data.repository.IpRepository
import kotlinx.coroutines.launch


@Composable
fun BuscarImpresora(
    navController: NavController,
    repository: DataRepository,
    repositoryAsignaImpresora: IpRepository,
    hardware: String?
) {

    var serial by remember { mutableStateOf("") }
    var hardwareId by remember { mutableStateOf(hardware ?: "") }
    var impresoraId by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()



    var btnRegistroImpresora by remember { mutableStateOf(true) }
    var btnAsignarImpresora by remember { mutableStateOf(false) }
    var btnRegistrarUsuario by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = serial,
            onValueChange = { serial = it },
            label = { Text("Ingrese el serial de la impresora") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                coroutineScope.launch {
                    try {
                        val response = repository.buscarImpresoraSerial(serial)
                        if (response.code() == 404) {
                            //btnRegistroImpresora = true
                        } else {
                            val idImpre = response.body()
                            impresoraId = idImpre?.IdImpresora.toString()
                            btnAsignarImpresora = true
                            btnRegistroImpresora = false
                        }
                    } catch (e: Exception) {
                        Log.d("Error", e.toString())
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar impresora")
        }
        Spacer(modifier = Modifier.height(6.dp))
        Button(
            onClick = {
                      navController.navigate("impresora/$hardwareId")
            },
            enabled = btnRegistroImpresora,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar impresora")
        }
        Spacer(modifier = Modifier.height(6.dp))
        Button(
            onClick = {
                coroutineScope.launch {
                    try {
                        if (hardwareId.isNotBlank() && impresoraId.isNotBlank()) {
                            val asignacion = AsignarImpresora(
                                IdImpresora = impresoraId.toInt()
                            )
                            val response = repositoryAsignaImpresora.asignarImpresora(hardwareId.toInt(), asignacion)
                            if (response.isSuccessful) {
                                btnRegistrarUsuario = true
                            }
                        } else {
                            Log.d("Error", "hardwareId or impresoraId is empty")
                        }
                    } catch (e: Exception) {
                        Log.d("Error", e.toString())
                    }
                }
            },
            enabled = btnAsignarImpresora,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Asignar impresora")
        }
        Spacer(modifier = Modifier.height(6.dp))
        Button(
            onClick = {
                navController.navigate("usuario")
            },
            enabled = btnRegistrarUsuario,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar usuario")
        }
    }

}