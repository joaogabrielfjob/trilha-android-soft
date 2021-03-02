package com.joaogabriel.netflix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class FormLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_login)

        Handler(Looper.getMainLooper()).postDelayed({
            abrirTelaLogin()
        }, 2000)
    }

    private fun abrirTelaLogin() {
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
    }
}