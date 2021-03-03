package com.joaogabriel.netflix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.joaogabriel.netflix.adapter.FilmesAdapter
import com.joaogabriel.netflix.databinding.ActivityDetalhesFilmesBinding
import com.joaogabriel.netflix.model.addFilmes
import com.squareup.picasso.Picasso

class DetalhesFilme : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesFilmesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetalhesFilmesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val recyclerOutrosFilmes = binding.recyclerOutrosFilmes
        recyclerOutrosFilmes.adapter = FilmesAdapter(addFilmes())
        recyclerOutrosFilmes.layoutManager = GridLayoutManager(applicationContext, 3)

        toolbarDetalhes()

        val capaTheWitcher = "https://firebasestorage.googleapis.com/v0/b/netflix-efc30.appspot.com/o/video.jpg?alt=media&token=5b48592c-d0f6-445d-9717-0966135225ad"
        Picasso.get().load(capaTheWitcher).fit().into(binding.capa)

        binding.playFilme.setOnClickListener {
            val intent = Intent(this, Video::class.java)

            startActivity(intent)
        }
    }

    private fun toolbarDetalhes() {
        val toolbarDetalhes = binding.toolbarDetalhes
        toolbarDetalhes.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_voltar)

        toolbarDetalhes.setNavigationOnClickListener {
            val intent = Intent(this, ListaFilmes::class.java)
            startActivity(intent)
            finish()
        }
    }
}