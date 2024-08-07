package com.example.bodegas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bodegas.data.repository.DataRepository
import com.example.bodegas.data.repository.IpRepository
import com.example.bodegas.ui.components.ActualizarContrasena
import com.example.bodegas.ui.components.AsignarUbicacion
import com.example.bodegas.ui.components.BuscarEquipo
import com.example.bodegas.ui.components.BuscarImpresora
import com.example.bodegas.ui.components.BuscarIpDisponible
import com.example.bodegas.ui.components.Equipo.EditEquipo
import com.example.bodegas.ui.components.FormularioEquipo
import com.example.bodegas.ui.components.FormularioHardware
import com.example.bodegas.ui.components.FormularioSoftware
import com.example.bodegas.ui.components.FormularioUsuario
import com.example.bodegas.ui.components.HomePage
import com.example.bodegas.ui.components.Impresora.FormRegistroIpImpresora
import com.example.bodegas.ui.components.Impresora.FormularioSinIp
import com.example.bodegas.ui.components.Impresora.TipoFormulario
import com.example.bodegas.ui.components.LoginPage


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val repository = DataRepository()
    val repositoryIp = IpRepository()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginPage(navController) }
        composable("actualizarContrasena/{rut}") { backStackEntry ->
            val rut = backStackEntry.arguments?.getString("rut")
            if (rut != null) {
                ActualizarContrasena(navController, rut)
            }
        }
        composable("home") { HomePage(navController) }
        composable("equipo/{ip}") { backStackEntry ->
            val ip = backStackEntry.arguments?.getString("ip")
            FormularioEquipo(navController, ip)
        }
        composable("hardware/{equipoId}") { backStackEntry ->
            val equipoId = backStackEntry.arguments?.getString("equipoId")
            FormularioHardware(navController, equipoId)
        }
        composable("software/{equipoId}") { backStackEntry ->
            val equipoId = backStackEntry.arguments?.getString("equipoId")
            FormularioSoftware(navController, equipoId)
        }
        composable("buscarImpresora/{hardware}") { backStackEntry ->
            val hardware = backStackEntry.arguments?.getString("hardware")
            BuscarImpresora(
                navController = navController,
                repository = repository,
                repositoryAsignaImpresora = repositoryIp,
                hardware = hardware
            )
        }
        composable("impresora/{hardware}") { backStackEntry ->
            val hardware = backStackEntry.arguments?.getString("hardware")
            TipoFormulario(navController, hardware)
        }
        composable("impresoraConIp/{hardware}") { backStackEntry ->
            val hardware = backStackEntry.arguments?.getString("hardware")
            FormRegistroIpImpresora(navController, hardware)
        }
        composable("impresoraSinIp/{hardware}") { backStackEntry ->
            val hardware = backStackEntry.arguments?.getString("hardware")
            FormularioSinIp(navController, hardware)
        }
        composable("usuario") { FormularioUsuario(navController) }
        composable("buscar") { BuscarEquipo(navController, repository) }
        composable("buscarip") { BuscarIpDisponible(navController, repositoryIp) }
        composable("asignarUbicacion") { AsignarUbicacion(navController = navController) }

        composable("editarEquipo/{idEquipo}") { backStackEntry ->
            val idEquipo = backStackEntry.arguments?.getString("idEquipo")
            if (idEquipo != null) {
                EditEquipo(idEquipo = idEquipo,navController = navController)
            }
        }
    }
}



