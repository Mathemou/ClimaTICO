package com.example.climatico.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.climatico.model.WeatherResponse
import com.example.climatico.repository.WeatherRepository
import kotlinx.coroutines.launch

class RelatoryViewModel (
    private val repository : WeatherRepository
) : ViewModel(){

    private var _weatherResponse : MutableLiveData<WeatherResponse> = MutableLiveData()
    val weatherResponse : LiveData<WeatherResponse> get() = _weatherResponse

    private val _error = MutableLiveData<String>()

    val error: LiveData<String> get() = _error

     fun loadWeather(cityName : String){
         viewModelScope.launch{
             try{
                 val result = repository.getWeather(cityName)
                 _weatherResponse.postValue(result)
             } catch (e : Exception){
                 _error.value = e.message
             }
         }

    }
}