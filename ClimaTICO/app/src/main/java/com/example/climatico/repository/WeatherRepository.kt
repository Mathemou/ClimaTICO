package com.example.climatico.repository

import com.example.climatico.API.WeatherAPI
import com.example.climatico.model.WeatherResponse

class WeatherRepository(private val api : WeatherAPI) {
    suspend fun getWeather(cityName : String) : WeatherResponse {
        return api.getWeatherByCityName(cityName)
    }
}