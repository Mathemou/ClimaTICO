package com.example.climatico.API

import com.example.climatico.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather")
    suspend fun getWeatherByCityName(
        @Query("q") city: String,
        @Query("appid") apiKey: String = "d2a083a6a3024cbb783c29d2f51ca46f", // Vou colocar isso no arquivo de constantes
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "pt_br"
    ) : WeatherResponse
}