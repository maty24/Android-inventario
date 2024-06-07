package com.example.bodegas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bodegas.ui.components.FormularioEquipo
import com.example.bodegas.ui.components.FormularioHardware
import com.example.bodegas.ui.components.FormularioImpresora
import com.example.bodegas.ui.components.FormularioSoftware
import com.example.bodegas.ui.components.HomePage


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomePage(navController) }
        composable("equipo") { FormularioEquipo(navController) {} }
        composable("hardware") { FormularioHardware(navController) }
        composable("software") { FormularioSoftware(navController) }
        composable("impresora") { FormularioImpresora(navController) }
    }
}