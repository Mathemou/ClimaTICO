package com.example.climatico.model

data class WeatherResponse(
    val name: String,            // Nome da cidade
    val weather: List<Weather>,  // Lista com as condições
    val main: Main,              // Dados principais (temp, pressão...)
    val wind: Wind               // Vento
)

data class Weather(
    val description: String,     // "clear sky"
    val icon: String             // "01d"
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int
)

data class Wind(
    val speed: Double
)
