package com.example.bodegas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bodegas.data.repository.DataRepository
import com.example.bodegas.ui.components.BuscarEquipo
import com.example.bodegas.ui.components.FormularioEquipo
import com.example.bodegas.ui.components.FormularioHardware
import com.example.bodegas.ui.components.FormularioImpresora
import com.example.bodegas.ui.components.FormularioSoftware
import com.example.bodegas.ui.components.HomePage


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val repository = DataRepository()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomePage(navController) }
        composable("equipo") { FormularioEquipo(navController) }
        composable("hardware/{equipoId}") { backStackEntry ->
            val equipoId = backStackEntry.arguments?.getString("equipoId")
            FormularioHardware(navController, equipoId)
        }
        composable("software") { backStackEntry ->
            val equipoId = backStackEntry.arguments?.getString("equipoId")
            FormularioSoftware(navController, equipoId)
        }
        composable("impresora") { FormularioImpresora(navController) }
        composable("buscar") { BuscarEquipo(navController, repository) }
    }
}