package com.joaogabriel.calculadoradefaltas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.joaogabriel.calculadoradefaltas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botaoCalcularMedia.setOnClickListener { calcularMedia() }
    }

    private fun calcularMedia() {
        val aluno = buscarDados()
        val media: Double

        if (aluno != null) {
            media = (aluno.nota1 + aluno.nota2 + aluno.nota3 + aluno.nota4) / 4

            if (aluno.faltas < 20 && media > 5) {
                binding.txtResultado.text = "O aluno foi aprovado"
                binding.txtResultado.setTextColor(ContextCompat.getColor(applicationContext, R.color.green))

                binding.txtMedia.text = "Sua média final é: $media"

                binding.txtResultado.visibility = View.VISIBLE
                binding.txtMedia.visibility = View.VISIBLE
            } else if (aluno.faltas > 20 || media < 5) {
                binding.txtResultado.text = "O aluno foi reprovado"
                binding.txtResultado.setTextColor(ContextCompat.getColor(applicationContext, R.color.red))

                binding.txtMedia.text = "Sua média final é: $media"

                binding.txtResultado.visibility = View.VISIBLE
                binding.txtMedia.visibility = View.VISIBLE
            }
        }
    }

    private fun buscarDados(): Aluno? {
        val nota1 = binding.txtNota1.text.toString().toDoubleOrNull()
        val nota2 = binding.txtNota1.text.toString().toDoubleOrNull()
        val nota3 = binding.txtNota1.text.toString().toDoubleOrNull()
        val nota4 = binding.txtNota1.text.toString().toDoubleOrNull()
        val faltas = binding.txtNota1.text.toString().toIntOrNull()

        return validarDados(nota1, nota2, nota3, nota4, faltas)
    }

    private fun validarDados(nota1: Double?,
                             nota2: Double?,
                             nota3: Double?,
                             nota4: Double?,
                             faltas: Int?): Aluno? {
        return if (nota1 != null &&
            nota2 != null &&
            nota3 != null &&
            nota4 != null &&
            faltas != null) {
                Aluno(nota1, nota2, nota3, nota4, faltas)
        } else {
            Toast.makeText(this, "Digite os dados corretamente", Toast.LENGTH_LONG).show()
            null
        }
    }
}

data class Aluno(
    val nota1: Double,
    val nota2: Double,
    val nota3: Double,
    val nota4: Double,
    val faltas: Int,
)