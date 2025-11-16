package com.example.climatico.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.climatico.repository.WeatherRepository

class RelatoryViewModelFactory (private val repository : WeatherRepository)  : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RelatoryViewModel::class.java)){
        return RelatoryViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Passa o bagulho (RelatoryViewModel) certo, animal")
        }
    }
}