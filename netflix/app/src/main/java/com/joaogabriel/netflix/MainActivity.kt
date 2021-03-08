package com.joaogabriel.netflix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            abrirTelaLogin()
        }, 2000)

        val notas: MutableList<String> = listOf<String>()
    }

    private fun abrirTelaLogin() {
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
    }
}