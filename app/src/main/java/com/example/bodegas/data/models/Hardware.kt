package com.example.bodegas.data.models


data class Hardware(
    val IDEquipo: Int,
    val NumeroInventario: String,
    val MarcaEquipo: String,
    val ModeloEquipo: String,
    val MarcaPlaca: String,
    val ModeloPlaca: String,
    val Procesador: String,
    val Ram: String,
    val TarjetaVideo: String,
    val Almacenamiento: String,
    val Huellero: Boolean,
    val Scanner: Boolean,
    val SerieScanner: String,
    val FirmaElectronica: Boolean,
    val LectorCodigoBarras: Boolean,
    val NumeroInventarioCodigoBarras: String,
    val Responsable: String
)


data class HardwareResponse(
    val IdComponente: Int
)