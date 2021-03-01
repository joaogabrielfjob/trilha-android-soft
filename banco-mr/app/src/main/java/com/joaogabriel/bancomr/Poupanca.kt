package com.joaogabriel.bancomr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.joaogabriel.bancomr.databinding.ActivityPoupancaBinding

class Poupanca : AppCompatActivity() {
    private lateinit var binding: ActivityPoupancaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPoupancaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val toolbar = binding.toolbarPoupanca
        toolbar.title = "Poupança"
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.light_blue))
        toolbar.setNavigationIcon(R.drawable.ic_voltar)
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}