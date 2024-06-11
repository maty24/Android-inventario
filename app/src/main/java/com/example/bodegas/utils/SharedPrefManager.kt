package com.example.bodegas.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(private val token: String) {

    fun getToken(): String {
        return token
    }

    // Los otros métodos ya no son necesarios ya que el token se pasa directamente
}