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
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val repository = LoginRepository() // Crea una instancia de LoginRepository
    val coroutineScope = rememberCoroutineScope()


    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
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
                    val login = Login(username, password)
                    val response = repository.login(login)
                    if (response.code() == 428) {
                        // Si el código de estado es 428, navega a "actualizarContrasena/username"
                        navController.navigate("actualizarContrasena/username")
                    }
                    // Llama a la función login
                    if (response.isSuccessful) {
                        val loginResponse = response.body()!!
                        loginResponse.let {
                            Global.token = it.token // Guarda el token en la variable global
                        }
                        navController.navigate("home")


                    } else {
                        // Muestra un mensaje de error si la respuesta no es exitosa
                        Log.e(
                            "LoginPage",
                            "Error al iniciar sesión: ${response.errorBody()?.string()}"
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Acceder")
        }
    }
}


