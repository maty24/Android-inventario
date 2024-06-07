package com.example.bodegas.data.repository

import com.example.bodegas.data.api.ApiModule
import com.example.bodegas.data.models.Equipo
import com.example.bodegas.data.models.EquipoResponse
import com.example.bodegas.data.models.Hardware
import retrofit2.Response

class DataRepository {
    suspend fun crearEquipo(equipo: Equipo): Response<Void> { // <-- Cambiamos el tipo de retorno
        return ApiModule.apiService.crearEquipo(equipo)
    }

    suspend fun crearHardware(hardware: Hardware): Response<Void> {
        return ApiModule.apiService.crearHardware(hardware)
    }

    suspend fun getEquipoPorMac(macAddress: String): Response<EquipoResponse> {
        return ApiModule.apiService.getEquipoPorMac(macAddress)
    }
}