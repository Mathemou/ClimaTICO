package com.example.climatico.api

import com.example.climatico.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    // Exemplo de endpoint:
    // https://api.openweathermap.org/data/2.5/weather?q=London&appid=YOUR_KEY&units=metric

    @GET("weather")
    suspend fun getWeatherByCity(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "pt_br"
    ): Response<WeatherResponse>
}
