package com.example.bodegas.data.models

data class Software(
    val idEquipo: Int,
    val EdicionSistemaOperativo: String,
    val VersionSistemaOperativo: String,
    val edicionOffice: String,
    val versionOffice: String,
    val edicionAntivirus: String,
    val versionAntivirus: String,
    val maquinaVirtual: String,
    val versionMaquinaVirtual: String,
    val otrosProgramas: String,
    val versionOtrosProgramas: String,
    val sistemaSoporteRemoto: Boolean,
    val nombreSoporteRemoto: String
)
