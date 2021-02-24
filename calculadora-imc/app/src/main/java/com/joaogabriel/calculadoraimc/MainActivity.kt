package com.joaogabriel.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.joaogabriel.calculadoraimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botaoCalcularImc.setOnClickListener { calcularImc() }
        binding.botaoLimparCampos.setOnClickListener { limparCampos() }
    }

    private fun calcularImc() {
        val peso = binding.campoPeso.text.toString().toInt()
        val altura = binding.campoAltura.text.toString().toDouble()
        val imc = peso / (altura * altura)

        val resultado = getString(R.string.imc, imc)
        binding.campoResultado.text = resultado
        binding.campoResultado.visibility = View.VISIBLE
    }

    private fun limparCampos() {
        binding.campoPeso.text.clear()
        binding.campoAltura.text.clear()
    }
}