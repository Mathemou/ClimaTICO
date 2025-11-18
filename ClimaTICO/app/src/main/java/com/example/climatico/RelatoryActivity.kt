package com.example.climatico

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.climatico.API.RetrofitClient
import com.example.climatico.databinding.ActivityRelatoryBinding
import com.example.climatico.model.WeatherResponse
import com.example.climatico.repository.WeatherRepository
import com.example.climatico.viewmodel.RelatoryViewModel
import com.example.climatico.viewmodel.RelatoryViewModelFactory
import com.google.android.material.appbar.MaterialToolbar


class RelatoryActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityRelatoryBinding
    private lateinit var cityName : String
    private lateinit var relatoryViewModel : RelatoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Carrega o layout APENAS com binding
        binding = ActivityRelatoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ajuste de insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar a toolbar do binding
        setSupportActionBar(binding.toolbar)

        // Ativar botão de voltar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Resto do código
        cityName = Utils.capitalizeFirstLetter(intent.getStringExtra("cityName")!!)
        binding.tvNomeDaCidade.text = cityName

        val repository = WeatherRepository(RetrofitClient.instance)
        val factory = RelatoryViewModelFactory(repository)

        relatoryViewModel = ViewModelProvider(this, factory)[RelatoryViewModel::class.java]

        relatoryViewModel.loadWeather(Utils.removeAccent(cityName))

        setObservers()
    }

    private fun setObservers() {
        relatoryViewModel.weatherResponse.observe(this){ weather ->
            setInformations(weather)
        }
        relatoryViewModel.error.observe(this){ error ->
            Toast.makeText(this, "Cidade não encontrada", Toast.LENGTH_SHORT).show()
            Log.d("error", error)
            finish()
        }
    }

    private fun setInformations(weather : WeatherResponse){
        binding.tvTemperaturaEAparencia.text = weather.main.temp.toString() + "ªC, " + weather.weather[0].description
        binding.tvTemperaturaMaxima.text = "Temperatura máxima: " + weather.main.temp_max.toString() + "ªC"
        binding.tvTemperaturaMinima.text = "Temperatura mínima: " + weather.main.temp_min.toString() + "ªC"
        binding.tvSensacaoTermica.text = "Sensação térmica: " + weather.main.feels_like.toString() + "ªC"
        binding.tvVelocidadeVento.text = "Velocidade do vento: " + weather.wind.speed.toString() + "km/h"
        binding.tvHumidade.text = "Umidade: " + weather.main.humidity.toString() + "%"
        val icon = weather.weather[0].icon

        Glide.with(this)
            .load("https://openweathermap.org/img/wn/$icon@2x.png")
            .into(binding.ivSiglaEstado)
        binding.pbCarregando.visibility = View.GONE
        binding.clConteudo.visibility = View.VISIBLE
    }

    override fun onClick(v: View) {
        when(v.id){

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}