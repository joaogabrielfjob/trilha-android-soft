package com.joaogabriel.blocodenotas

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.joaogabriel.blocodenotas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferencia = PreferenciaAnotacao(applicationContext)
        val botaoSalvar = binding.fab

        botaoSalvar.setOnClickListener {
            val anotacaoRecuperada = binding.editContainer.editAnotacao.text.toString()

            if (anotacaoRecuperada == "") {
                Toast.makeText(this, "Digite alguma coisa...", Toast.LENGTH_SHORT).show()
            } else {
                preferencia.salvarAnotacao(anotacaoRecuperada)
                Toast.makeText(this, "Anotação salva com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }

        val anotacao = preferencia.recuperarAnotacao()
        if (anotacao != "") {
            binding.editContainer.editAnotacao.setText(anotacao)
        }
    }
}