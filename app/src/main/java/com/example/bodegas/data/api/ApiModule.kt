package com.example.bodegas.data.api

import com.example.bodegas.utils.Global
import com.example.bodegas.utils.SharedPrefManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiIpModule {
    private const val BASE_URL = "http://ws.hcsba.cl/isa2-inventario/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiIpService: ApiIpService = retrofit.create(ApiIpService::class.java)
}


object ApiModule {
    private const val BASE_URL = "http://ws.hcsba.cl/isa2-inventario-create/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val sharedPrefManager = SharedPrefManager(token = Global.token ?: "")
                val token = sharedPrefManager.getToken()
                request.addHeader("Authorization", "Bearer $token")
                chain.proceed(request.build())
            }.build()
        )
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}

object ApiLoginModule {
    private const val BASE_URL = "http://ws.hcsba.cl/isa2-tecnicos/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiLoginService: ApiLoginService = retrofit.create(ApiLoginService::class.java)
}

