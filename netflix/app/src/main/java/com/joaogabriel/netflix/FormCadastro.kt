package com.joaogabriel.netflix

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.joaogabriel.netflix.databinding.ActivityFormCadastroBinding

class FormCadastro : AppCompatActivity() {
    private lateinit var binding: ActivityFormCadastroBinding
    private lateinit var firebaseAuth: FirebaseAuth

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        firebaseAuth = FirebaseAuth.getInstance()
        toolbar()

        binding.btCadastrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val mensagemErro = binding.mensagemErro

            if (email.isEmpty() || senha.isEmpty()) {
                mensagemErro.text = "Preencha todos os campos"
            } else {
                cadastrarUsuario()
            }
        }
    }

    private fun cadastrarUsuario() {
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val mensagemErro = binding.mensagemErro

        firebaseAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Usuario cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                binding.editEmail.text.clear()
                binding.editSenha.text.clear()
                mensagemErro.text = ""
            }
        }.addOnFailureListener {
            var erro = it

            when {
                erro is FirebaseAuthWeakPasswordException -> mensagemErro.text = "Digite uma senha com no minimo 6 caracteres"
                erro is FirebaseAuthUserCollisionException -> mensagemErro.text = "Este email ja foi cadastrado"
                erro is FirebaseNetworkException -> mensagemErro.text = "Sem conexao com a internet"

                else -> mensagemErro.text = "Erro ao cadastrar usuario"
            }
        }
    }

    private fun toolbar() {
        val toolbar = binding.toolbarCadastro
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_netflix_official_logo)
    }
}