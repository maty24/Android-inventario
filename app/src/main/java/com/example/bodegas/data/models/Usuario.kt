package com.example.bodegas.data.models


data class Usuario(
    val Rut: String,
    val Dv: String,
    val NombreFuncionario: String,
    val Correo: String,
    val Anexo: String,
    val TipoEquipo: String,
    val TipoUso: String
)

data class UsuarioResponse(
    val IdUsuario: Int
)