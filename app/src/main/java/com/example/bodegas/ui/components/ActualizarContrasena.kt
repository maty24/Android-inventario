package com.example.bodegas.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bodegas.data.models.ActializarContrasena
import com.example.bodegas.data.repository.LoginRepository
import kotlinx.coroutines.launch

@Composable
fun ActualizarContrasena(
    navController: NavHostController,
    rut: String?
) {
    var rutState by remember { mutableStateOf(rut) }
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val repository = LoginRepository()
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = oldPassword,
            onValueChange = { oldPassword = it },
            label = { Text("Contraseña actual") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text("Nueva contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar nueva contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (newPassword == confirmPassword) {
                    coroutineScope.launch {
                        val actulizarContrasena = ActializarContrasena(
                            Rut = rutState!!,
                            Contrasena = oldPassword,
                            ContrasenaNueva = newPassword
                        )

                        Log.d(
                            "ActualizarContrasena",
                            "Rut: ${actulizarContrasena.Rut}, Contrasena: ${actulizarContrasena.Contrasena}, ContrasenaNueva: ${actulizarContrasena.ContrasenaNueva}"
                        )
                        val response = repository.actualizarContrasena(actulizarContrasena)
                        if (response.isSuccessful) {
                            navController.navigate("login")
                        } else {
                            // Manejar el caso en que la respuesta no sea exitosa
                            Log.e(
                                "LoginPage",
                                "Error al iniciar sesión: ${response.errorBody()?.string()}"
                            )
                        }
                    }
                } else {
                    Log.e("ActualizarContrasena", "Las contraseñas no coinciden")
                    // You can also show a message to the user here
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar contraseña")
        }
    }
}