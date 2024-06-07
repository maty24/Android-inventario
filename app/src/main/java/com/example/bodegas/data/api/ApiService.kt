package com.example.bodegas.data.api

import com.example.bodegas.data.models.Equipo
import com.example.bodegas.data.models.EquipoResponse
import com.example.bodegas.data.models.Hardware
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("equipos")
    suspend fun crearEquipo(@Body equipo: Equipo): Response<Void> // Asumiendo que la API no devuelve nada en el cuerpo

    @POST("componentes_hardware/")
    suspend fun crearHardware(@Body hardware: Hardware): Response<Void>

    @GET("equipos/mac/{macAddress}")
    suspend fun getEquipoPorMac(@Path("macAddress") macAddress: String): Response<EquipoResponse>
}