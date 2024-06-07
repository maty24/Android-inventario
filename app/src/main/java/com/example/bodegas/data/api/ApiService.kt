package com.example.bodegas.data.api

import com.example.bodegas.data.models.Equipo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("equipos")
    suspend fun crearEquipo(@Body equipo: Equipo): Response<Void> // Asumiendo que la API no devuelve nada en el cuerpo
}