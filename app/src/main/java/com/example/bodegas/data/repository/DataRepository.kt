package com.example.bodegas.data.repository

import com.example.bodegas.data.api.ApiIpModule
import com.example.bodegas.data.api.ApiLoginModule
import com.example.bodegas.data.api.ApiModule
import com.example.bodegas.data.models.ActializarContrasena
import com.example.bodegas.data.models.Equipo
import com.example.bodegas.data.models.EquipoResponse
import com.example.bodegas.data.models.EquipoResponseCreate
import com.example.bodegas.data.models.Hardware
import com.example.bodegas.data.models.Impresora
import com.example.bodegas.data.models.IpDisponibleResponse
import com.example.bodegas.data.models.Login
import com.example.bodegas.data.models.LoginResponse
import com.example.bodegas.data.models.Software
import com.example.bodegas.data.models.Usuario
import retrofit2.Response

class DataRepository {
    suspend fun crearEquipo(equipo: Equipo): Response<EquipoResponseCreate> {
        return ApiModule.apiService.crearEquipo(equipo)
    }

    suspend fun crearHardware(hardware: Hardware): Response<Void> {
        return ApiModule.apiService.crearHardware(hardware)
    }

    suspend fun crearSoftware(software: Software): Response<Void> {
        return ApiModule.apiService.crearSoftware(software)
    }

    suspend fun crearImpresora(impresora: Impresora): Response<Void> {
        return ApiModule.apiService.crearImpresora(impresora)
    }

    suspend fun getEquipoPorMac(macAddress: String): Response<EquipoResponse> {
        return ApiModule.apiService.getEquipoPorMac(macAddress)
    }

    suspend fun crearUsuario(usuario: Usuario): Response<Void> {
        return ApiModule.apiService.crearUsuario(usuario)
    }

}

class LoginRepository {
    suspend fun login(login: Login): Response<LoginResponse> {
        return ApiLoginModule.apiLoginService.login(login)
    }

    suspend fun actualizarContrasena(actualizarContrasena: ActializarContrasena): Response<Void> {
        return ApiLoginModule.apiLoginService.actualizarContrasena(actualizarContrasena)
    }
}

class IpRepository {
    suspend fun verificarIp(ip: String): Response<IpDisponibleResponse> {
        return ApiIpModule.apiIpService.verificarIp(ip)
    }
}