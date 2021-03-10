package com.joaogabriel.marvel.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joaogabriel.marvel.databinding.CharacterComicsItemsBinding
import com.joaogabriel.marvel.model.MarvelResult

class DetailsAdapter(private val character: MarvelResult): RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {
    inner class DetailsViewHolder(val binding: CharacterComicsItemsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val binding = CharacterComicsItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        with(holder) {
            binding.txtComicTitle.text = character.comics.items[position].name
        }
    }

    override fun getItemCount(): Int = character.comics.items.size
}