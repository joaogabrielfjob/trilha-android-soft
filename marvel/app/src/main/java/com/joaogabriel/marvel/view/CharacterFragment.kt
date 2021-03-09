package com.joaogabriel.marvel.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.joaogabriel.marvel.R
import com.joaogabriel.marvel.model.repository.CharacterRepository
import com.joaogabriel.marvel.utils.Resource
import com.joaogabriel.marvel.viewmodel.CharacterViewModel

class CharacterFragment : Fragment(R.layout.fragment_character) {
    private lateinit var characterViewModel: CharacterViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterViewModel = ViewModelProvider(this,
        CharacterViewModel.CharacterViewModelFactory(CharacterRepository())).
        get(CharacterViewModel::class.java)

        characterViewModel.characterResponse.observe(viewLifecycleOwner, { response ->
            when(response) {
                is Resource.Success -> {
                    println(response.data?.data?.results)
                }

                is Resource.Error -> {
                    println(response.message)
                }

                is Resource.Loading -> {
                    println("carregando")
                }
            }
        })

        characterViewModel.getCharacters()
    }
}