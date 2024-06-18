package com.example.bodegas.ui.components.Impresora

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bodegas.data.models.AsignarImpresora
import com.example.bodegas.data.models.ImpersoraIdIp
import com.example.bodegas.data.repository.DataRepository
import com.example.bodegas.data.repository.IpRepository
import kotlinx.coroutines.launch


@Composable
fun FormRegistroIpImpresora(navController: NavHostController, hardware: String?) {


    var IdComponente by remember { mutableIntStateOf(hardware?.toInt() ?: 0) }
    var IdIp by remember { mutableIntStateOf(0) }
    var idImpresora by remember { mutableIntStateOf(0) }
    var ipImpresora by remember { mutableStateOf("") }

    var activarTextField by remember { mutableStateOf(false) }
    var activarBtnRegistrar by remember { mutableStateOf(false) }
    var activarBtnAsignar by remember { mutableStateOf(false) }

    var NumeroSerie by remember { mutableStateOf("") }
    var Marca by remember { mutableStateOf("") }
    var Modelo by remember { mutableStateOf("") }
    var TipoInterface by remember { mutableIntStateOf(2) }
    var TipoUso by remember { mutableStateOf("") }
    var Proveedor by remember { mutableStateOf("") }

    val respositoryIp = remember { IpRepository() }
    val repository = remember { DataRepository() }
    val scope = rememberCoroutineScope()



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Registro de impresora con IP",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = ipImpresora,
            onValueChange = { ipImpresora = it },
            label = { Text("IP Impresora") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                scope.launch {
                    val responseIp = respositoryIp.buscarImpresoraIp(ipImpresora)
                    if (responseIp.isSuccessful) {
                        IdIp = responseIp.body()?.id ?: 0
                        activarTextField = true
                    } else {
                        activarTextField = false
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Buscar IP")
        }
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = NumeroSerie,
            onValueChange = { NumeroSerie = it },
            label = { Text("Número de serie") },
            modifier = Modifier.fillMaxWidth(),
            enabled = activarTextField
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = Marca,
            onValueChange = { Marca = it },
            label = { Text("Marca") },
            modifier = Modifier.fillMaxWidth(),
            enabled = activarTextField
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = Modelo,
            onValueChange = { Modelo = it },
            label = { Text("Modelo") },
            modifier = Modifier.fillMaxWidth(),
            enabled = activarTextField
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = TipoUso,
            onValueChange = { TipoUso = it },
            label = { Text("Tipo de uso") },
            modifier = Modifier.fillMaxWidth(),
            enabled = activarTextField
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = Proveedor,
            onValueChange = { Proveedor = it },
            label = { Text("Proveedor") },
            modifier = Modifier.fillMaxWidth(),
            enabled = activarTextField
        )
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = {
                scope.launch {
                    val impresoraIp = ImpersoraIdIp(
                        NumeroSerie = NumeroSerie,
                        Marca = Marca,
                        Modelo = Modelo,
                        TipoInterface = TipoInterface,
                        TipoUso = TipoUso,
                        Proveedor = Proveedor,
                        IdIpImpresora = IdIp
                    )
                    val response = repository.crearImpresoraIpId(impresoraIp)
                    if (response.isSuccessful) {
                        idImpresora = response.body()?.IdImpresora!!

                        activarBtnRegistrar = false
                        activarBtnAsignar = true
                    } else {
                        activarBtnRegistrar = false
                    }
                }
            },
            enabled = activarTextField,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Registrar impresora")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = {
                scope.launch {
                    val asignacion = AsignarImpresora(IdImpresora = idImpresora)
                    val responseAsignacion =
                        respositoryIp.asignarImpresora(IdComponente, asignacion)
                    try {
                        if (responseAsignacion.isSuccessful) {
                            activarBtnAsignar = false
                        } else {

                        }
                    } catch (e: Exception) {
                        Log.e("Error", e.toString())
                    }
                }
            },
            enabled = activarBtnAsignar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Asignar impresora")
        }
    }
}