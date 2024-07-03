package com.example.bodegas.ui.components.Equipo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bodegas.data.repository.DataRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditEquipo(
    idEquipo: String
) {

    var IDEquipo by remember { mutableIntStateOf(idEquipo?.replace(".", "")?.toIntOrNull() ?: 0) }
    val repository = remember { DataRepository() }

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

    var expandedOption by remember { mutableStateOf(false) }
    val options = listOf("255.255.255.0", "255.255.254.0", "255.255.252.0")
    var MascaraRed by remember { mutableStateOf(options[0]) }

    LaunchedEffect(IDEquipo) {
        if (IDEquipo > 0) {
            val response = repository.getEquipoPorId(IDEquipo)
            if (response.isSuccessful) {
                response.body()?.let { equipo ->
                    PuertaEnlace = equipo.PuertaEnlace
                    DnsPrimario = equipo.DnsPrimario
                    DnsSecundario = equipo.DnsSecundario
                    MacAddress = equipo.MacAddress
                    MiniSwitch = equipo.MiniSwitch
                    IpSwitch = equipo.IpSwitch
                    PuertoSwitch = equipo.PuertoSwitch
                    NombreEquipo = equipo.NombreEquipo
                    NombreUsuarioPC = equipo.NombreUsuarioPC
                    Dominio = equipo.Dominio
                    MascaraRed = if (equipo.MascaraRed in options) equipo.MascaraRed else ""
                }
            } else {
                // Manejar error
            }
        }
    }


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
        Text(text = "Mascara de Red")
        ExposedDropdownMenuBox(
            expanded = expandedOption,
            onExpandedChange = { expandedOption = !expandedOption }
        ) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = MascaraRed,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedOption) }
            )
            ExposedDropdownMenu(
                expanded = expandedOption,
                onDismissRequest = { expandedOption = false }) {
                options.forEachIndexed { index, text ->
                    DropdownMenuItem(
                        text = { Text(text = text) },
                        onClick = {
                            MascaraRed = options[index]
                            expandedOption = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
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


    }
}