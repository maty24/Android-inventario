package com.example.bodegas.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bodegas.data.models.Login
import com.example.bodegas.data.repository.LoginRepository
import com.example.bodegas.utils.Global
import com.example.bodegas.utils.SharedPrefManager
import kotlinx.coroutines.launch


@Composable
fun LoginPage(navController: NavHostController) {
    var rut by remember { mutableStateOf("") }
    var dv by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val repository = LoginRepository() // Crea una instancia de LoginRepository
    val coroutineScope = rememberCoroutineScope()
    var isRequestInProgress by remember { mutableStateOf(false) }

    var showDialogRellenarCampos by remember { mutableStateOf(false) }
    var showDialogError by remember { mutableStateOf(false) }
    var showDialogErroCredenciales by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = rut,
            onValueChange = { rut = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                coroutineScope.launch {
                    isRequestInProgress = true // Inicia la solicitud
                    try {
                        if (rut.isNotEmpty()) {
                            dv = rut.last().toString()
                            rut = rut.dropLast(1)
                            username = "$rut-$dv"
                        }
                        //validar que los campos no esten vacios
                        if (rut.isEmpty() || password.isEmpty()) {
                            // Muestra un mensaje de error si los campos están vacíos
                            showDialogRellenarCampos = true
                            return@launch // Termina la coroutine aquí si los campos están vacíos
                        }
                        val login = Login(username, password)
                        val response = repository.login(login)
                        if (response.code() == 428) {
                            // Si el código de estado es 428, navega a "actualizarContrasena/username"
                            navController.navigate("actualizarContrasena/$username")
                        }
                        // Llama a la función login
                        if (response.isSuccessful) {
                            val loginResponse = response.body()!!
                            loginResponse.let {
                                Global.token = it.token // Guarda el token en la variable global
                            }
                            navController.navigate("home")
                        } else {
                            showDialogErroCredenciales = true
                        }
                    } catch (e: java.net.SocketTimeoutException) {
                        // Maneja la excepción aquí
                        Log.e("LoginPage", "Tiempo de espera agotado al intentar iniciar sesión", e)
                        showDialogError = true

                    } finally {
                        isRequestInProgress = false // Finaliza la solicitud
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isRequestInProgress // Deshabilita el botón si la solicitud está en curso
        ) {
            Text("Acceder")
        }
        if (showDialogRellenarCampos) {
            // Muestra un diálogo si los campos están vacíos
            AlertDialog(
                onDismissRequest = { showDialogRellenarCampos = false },
                title = { Text("Error") },
                text = { Text("Por favor, rellene todos los campos") },
                confirmButton = {
                    Button(onClick = { showDialogRellenarCampos = false }) {
                        Text("Aceptar")
                    }
                }
            )
        }
        if (showDialogError) {
            // Muestra un diálogo si la respuesta no es exitosa
            AlertDialog(
                onDismissRequest = { showDialogError = false },
                title = { Text("Error") },
                text = { Text("Error al iniciar sesión") },
                confirmButton = {
                    Button(onClick = { showDialogError = false }) {
                        Text("Aceptar")
                    }
                }
            )
        }
        if (showDialogErroCredenciales) {
            // Muestra un diálogo si las credenciales son incorrectas
            AlertDialog(
                onDismissRequest = { showDialogErroCredenciales = false },
                title = { Text("Error") },
                text = { Text("Credenciales incorrectas") },
                confirmButton = {
                    Button(onClick = { showDialogErroCredenciales = false }) {
                        Text("Aceptar")
                    }
                }
            )
        }
    }
}