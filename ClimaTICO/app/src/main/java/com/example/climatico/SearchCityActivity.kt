package com.example.climatico

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.climatico.databinding.ActivitySearchCityBinding

class SearchCityActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivitySearchCityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search_city)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivitySearchCityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btSearch.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            binding.btSearch.id -> {
                val cityName = binding.etNomeCidade.text.toString()
                val bundle = Bundle()
                bundle.putString("cityName", cityName)
                val intent = Intent(this, RelatoryActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }
}