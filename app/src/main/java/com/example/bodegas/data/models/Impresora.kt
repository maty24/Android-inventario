package com.example.bodegas.data.models


data class Impresora(
    val NumeroSerie: String,
    val Marca: String,
    val Modelo: String,
    val TipoInterface: Int,
    val TipoUso: String
)

data class ImpresoraResponseSerial(
    val IdImpresora: Int
)

data class AsignarImpresora(
    val IdImpresora: Int,
    val IdComponente: Int
)