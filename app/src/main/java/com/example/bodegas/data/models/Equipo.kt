package com.example.bodegas.data.models



data class Equipo(
    val MascaraRed: String,
    val PuertaEnlace: String,
    val DnsPrimario: String,
    val DnsSecundario: String,
    val MacAddress: String,
    val MiniSwitch: String,
    val IpSwitch: String,
    val PuertoSwitch: Int,
    val NombreEquipo: String,
    val NombreUsuarioPC: String,
    val Dominio: String
)