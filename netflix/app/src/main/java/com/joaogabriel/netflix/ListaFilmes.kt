package com.joaogabriel.netflix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.joaogabriel.netflix.databinding.ActivityListaFilmesBinding

class ListaFilmes : AppCompatActivity() {
    private lateinit var binding: ActivityListaFilmesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListaFilmesBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.deslogar -> {
                FirebaseAuth.getInstance().signOut()
                voltarTelaLogin()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun voltarTelaLogin() {
        val intent = Intent(this, FormLogin::class.java)

        startActivity(intent)
        finish()
    }
}