package com.example.bodegas.ui.components.Impresora

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TipoFormulario() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "¿Qué tipo de formulario deseas?",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Aquí va la lógica para el registro con IP */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registro con IP")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* Aquí va la lógica para el registro sin IP */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registro sin IP")
        }
    }
}