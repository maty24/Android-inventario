package com.example.bodegas.data.api

import com.example.bodegas.data.models.ActializarContrasena
import com.example.bodegas.data.models.AsignacionesEquipo
import com.example.bodegas.data.models.AsignarImpresora
import com.example.bodegas.data.models.AsignarUbicacionId
import com.example.bodegas.data.models.Equipo
import com.example.bodegas.data.models.EquipoActualizado
import com.example.bodegas.data.models.EquipoResponse
import com.example.bodegas.data.models.EquipoResponseCreate
import com.example.bodegas.data.models.EquipoResponseId
import com.example.bodegas.data.models.Hardware
import com.example.bodegas.data.models.HardwareResponse
import com.example.bodegas.data.models.ImpersoraIdIp
import com.example.bodegas.data.models.Impresora
import com.example.bodegas.data.models.ImpresoraResponseSerial
import com.example.bodegas.data.models.IpDisponibleResponse
import com.example.bodegas.data.models.IpImpresoraResponse
import com.example.bodegas.data.models.Login
import com.example.bodegas.data.models.LoginResponse
import com.example.bodegas.data.models.Software
import com.example.bodegas.data.models.UbicacionResponse
import com.example.bodegas.data.models.Usuario
import com.example.bodegas.data.models.UsuarioResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("equipos/")
    suspend fun crearEquipo(@Body equipo: Equipo): Response<EquipoResponseCreate> // Asumiendo que la API no devuelve nada en el cuerpo

    @POST("componentes_hardware/")
    suspend fun crearHardware(@Body hardware: Hardware): Response<HardwareResponse>

    @POST("software_instalado/")
    suspend fun crearSoftware(@Body software: Software): Response<Void>

    @POST("impresora/")
    suspend fun crearImpresora(@Body impresora: Impresora): Response<ImpresoraResponseSerial>

    @POST("impresora/")
    suspend fun crearImpresoraIpId(@Body impresoraIp: ImpersoraIdIp): Response<ImpresoraResponseSerial>

    @POST("usuario/")
    suspend fun crearUsuario(@Body usuario: Usuario): Response<UsuarioResponse>

    @GET("equipos/ip/{ip}")
    suspend fun getEquipoPorMac(@Path("ip") macAddress: String): Response<EquipoResponse>

    @GET("impresora/serie/{serial}")
    suspend fun buscarImpresoraSerial(@Path("serial") serial: String): Response<ImpresoraResponseSerial>

    @GET("ubicaciones/piso/{piso}")
    suspend fun getUbicacionesPorPiso(@Path("piso") piso: String): Response<List<UbicacionResponse>>

    @GET("equipos/{id}")
    suspend fun getEquipoPorId(@Path("id") id: Int): Response<EquipoResponseId>

    @PATCH("equipos/{id}")
    suspend fun editarEquipo(@Path("id") id: Int, @Body equipo: EquipoActualizado): Response<Void>
}

interface ApiLoginService {
    @POST("login")
    suspend fun login(@Body login: Login): Response<LoginResponse>

    @POST("actualizar_contrasena")
    suspend fun actualizarContrasena(@Body actualizarContrasena: ActializarContrasena): Response<Void>

}

interface ApiIpService {
    @GET("ip_disponibles/ip/{ip}")
    suspend fun verificarIp(@Path("ip") ip: String): Response<IpDisponibleResponse>

    @PATCH("asignaciones/asignar_impresora/{IdComponente}")
    suspend fun asignarImpresora(
        @Path("IdComponente") IdComponente: Int,
        @Body asignarImpresora: AsignarImpresora
    ): Response<Void>

    @GET("ip_impresoras/ip/{ip}")
    suspend fun verificarIpImpresora(@Path("ip") ip: String): Response<IpImpresoraResponse>

    @POST("asignaciones/equipo_asignacion")
    suspend fun asignarEquipo(@Body asignarEquipo: AsignacionesEquipo): Response<Void>

    @PATCH("asignaciones/asignar_ubicacion/{IdEquipo}")
    suspend fun asignarUbicacion(
        @Path("IdEquipo") IdEquipo: String,
        @Body asignarUbicacion: AsignarUbicacionId
    ): Response<Void>


}