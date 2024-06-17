package com.example.bodegas.data.models


data class Impresora(
    val NumeroSerie: String,
    val Marca: String,
    val Modelo: String,
    val TipoInterface: Int,
    val TipoUso: String
)

data class ImpersoraIdIp(
    val NumeroSerie: String,
    val Marca: String,
    val Modelo: String,
    val TipoInterface: Int,
    val TipoUso: String,
    val IdIpImpresora: Int
)

data class ImpresoraResponseSerial(
    val IdImpresora: Int
)

data class AsignarImpresora(
    val IdImpresora: Int
)