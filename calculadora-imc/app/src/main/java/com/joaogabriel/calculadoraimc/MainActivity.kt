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

    private fun limparCampos() {
        binding.campoPeso.text.clear()
        binding.campoAltura.text.clear()
    }

    private fun buscarDados(): Map<String, Double> {
        val peso = binding.campoPeso.text.toString().toDouble()
        val altura = binding.campoAltura.text.toString().toDouble()

        return mapOf("peso" to peso, "altura" to altura)
    }

    private fun calcularImc() {
        val dados = buscarDados()
        val imc = dados.getValue("peso") / (dados.getValue("altura") * dados.getValue("altura"))

        val resultado = getString(R.string.imc, imc)
        binding.campoResultado.text = resultado
        binding.campoResultado.visibility = View.VISIBLE
    }
}