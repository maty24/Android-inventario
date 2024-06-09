package com.example.bodegas.data.api

import com.example.bodegas.data.models.Equipo
import com.example.bodegas.data.models.EquipoResponse
import com.example.bodegas.data.models.EquipoResponseCreate
import com.example.bodegas.data.models.Hardware
import com.example.bodegas.data.models.Impresora
import com.example.bodegas.data.models.Software
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("equipos/")
    suspend fun crearEquipo(@Body equipo: Equipo): Response<EquipoResponseCreate> // Asumiendo que la API no devuelve nada en el cuerpo

    @POST("componentes_hardware/")
    suspend fun crearHardware(@Body hardware: Hardware): Response<Void>

    @POST("software_instalado/")
    suspend fun crearSoftware(@Body software: Software): Response<Void>

    @POST("impresora/")
    suspend fun crearImpresora(@Body impresora: Impresora): Response<Void>

    @GET("equipos/mac/{macAddress}")
    suspend fun getEquipoPorMac(@Path("macAddress") macAddress: String): Response<EquipoResponse>
}