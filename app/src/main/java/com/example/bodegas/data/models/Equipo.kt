package com.example.bodegas.data.models


data class Equipo(
    val IDIpDisponible: Int,
    val MascaraRed: String,
    val PuertaEnlace: String,
    val DnsPrimario: String,
    val DnsSecundario: String,
    val MacAddress: String,
    val MiniSwitch: String,
    val IpSwitch: String,
    val PuertoSwitch: String,
    val NombreEquipo: String,
    val NombreUsuarioPC: String,
    val Dominio: String
)

data class EquipoResponseCreate(
    val IDEquipo: Int
)

data class EquipoResponse(
    val IDEquipo: Int,
    val FaltaEnComponenteHardware: Boolean,
    val FaltaEnSoftwareInstalado: Boolean,
    val FaltaUbicacion: Boolean,
)