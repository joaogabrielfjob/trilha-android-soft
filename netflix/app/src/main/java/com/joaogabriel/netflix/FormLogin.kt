package com.joaogabriel.netflix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.joaogabriel.netflix.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {
    private lateinit var binding: ActivityFormLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        firebaseAuth = FirebaseAuth.getInstance()

        binding.txtTelaCadastro.setOnClickListener {
            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
        }

        binding.btEntrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val mensagemErro = binding.mensagemErro

            if (email.isEmpty() || senha.isEmpty()) {
                mensagemErro.text = "Preencha todos os campos"
            } else {
                autenticarUsuario()
            }
        }
    }

    private fun autenticarUsuario() {
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val mensagemErro = binding.mensagemErro

        firebaseAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show()
                irParaTelaDeFilmes()
            }
        }.addOnFailureListener {
            var erro = it

            when {
                erro is FirebaseAuthInvalidCredentialsException -> mensagemErro.text = "Email ou senha invalidos"
                erro is FirebaseNetworkException -> mensagemErro.text = "Sem conexao com a internet"

                else -> mensagemErro.text = "Erro ao entrar"
            }
        }
    }

    private fun verificarUsuario() {
        val usuarioLogado = firebaseAuth.currentUser

        if (usuarioLogado != null) {
            irParaTelaDeFilmes()
        }
    }

    private fun irParaTelaDeFilmes() {
        val intent = Intent(this, ListaFilmes::class.java)

        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        verificarUsuario()
    }
}