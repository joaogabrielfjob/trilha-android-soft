package com.joaogabriel.marvel.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joaogabriel.marvel.databinding.CharacterItemsBinding
import com.joaogabriel.marvel.model.MarvelResults
import com.joaogabriel.marvel.utils.CharacterUtils

class CharacterAdapter(private val characters: MarvelResults, private val characterFragment: CharacterFragment, private val context: Context): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    inner class CharacterViewHolder(val binding: CharacterItemsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val characterUtils = CharacterUtils()

        with(holder) {
            with(characters.results[position]) {
                binding.txtCharacterName.text = name

                binding.btnShowDetails.setOnClickListener {
                    characterFragment.openCharacterDetails(this)
                }

                characterUtils.setCharacterImage(context, thumbnail.getPathExtension(), binding.imgCharacterImage)
            }
        }
    }

    override fun getItemCount(): Int = characters.results.size
}