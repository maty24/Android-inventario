package com.example.bodegas.data.models

data class Login(
    val Rut: String,
    val Contrasena: String
)

data class LoginResponse(
    val rut: String,
    val token: String
)

data class ActializarContrasena(
    val Rut: String,
    val Contrasena: String,
    val ContrasenaNueva: String
)