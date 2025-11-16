package com.example.climatico

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.climatico.API.RetrofitClient
import com.example.climatico.databinding.ActivityRelatoryBinding
import com.example.climatico.repository.WeatherRepository
import com.example.climatico.viewmodel.RelatoryViewModel
import com.example.climatico.viewmodel.RelatoryViewModelFactory

class RelatoryActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityRelatoryBinding

    private lateinit var cityName : String

    private lateinit var relatoryViewModel : RelatoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_relatory)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityRelatoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cityName = intent.getStringExtra("cityName")!!
        binding.tvNomeDaCidade.text = cityName
        val repository = WeatherRepository(RetrofitClient.instance)
        val factory = RelatoryViewModelFactory(repository)

        relatoryViewModel = ViewModelProvider(this, factory)[RelatoryViewModel::class.java]

        relatoryViewModel.loadWeather(cityName)

        setObservers()
    }

    private fun setObservers() {
        relatoryViewModel.weatherResponse.observe(this){ weather ->
            Log.d("RelatoryActivityObserver", "setObservers: $weather")
        }
        relatoryViewModel.error.observe(this){ error ->
            Log.d("RelatoryActivityObserver", "setObservers: $error")
        }
    }

    override fun onClick(v: View) {
        when(v.id){

        }
    }
}