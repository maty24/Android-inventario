package com.example.bodegas.data.repository

import com.example.bodegas.data.api.ApiModule
import com.example.bodegas.data.models.Equipo
import retrofit2.Response

class DataRepository {
    suspend fun crearEquipo(equipo: Equipo): Response<Void> { // <-- Cambiamos el tipo de retorno
        return ApiModule.apiService.crearEquipo(equipo)
    }
}