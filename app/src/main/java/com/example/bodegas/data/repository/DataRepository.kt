package com.example.bodegas.data.repository

import com.example.bodegas.data.api.ApiModule
import com.example.bodegas.data.models.Equipo
import com.example.bodegas.data.models.EquipoResponse
import com.example.bodegas.data.models.EquipoResponseCreate
import com.example.bodegas.data.models.Hardware
import com.example.bodegas.data.models.Impresora
import com.example.bodegas.data.models.Software
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

}