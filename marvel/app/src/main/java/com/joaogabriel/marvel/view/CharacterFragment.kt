package com.joaogabriel.marvel.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.joaogabriel.marvel.R
import com.joaogabriel.marvel.databinding.FragmentCharacterBinding
import com.joaogabriel.marvel.model.MarvelResult
import com.joaogabriel.marvel.model.repository.CharacterRepository
import com.joaogabriel.marvel.utils.Resource
import com.joaogabriel.marvel.viewmodel.CharacterViewModel
import com.joaogabriel.marvel.viewmodel.DetailsViewModel

class CharacterFragment : Fragment(R.layout.fragment_character) {
    private lateinit var binding: FragmentCharacterBinding
    private lateinit var characterViewModel: CharacterViewModel
    private val detailsViewModel: DetailsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCharacterBinding.bind(view)

        characterViewModel = ViewModelProvider(this,
        CharacterViewModel.CharacterViewModelFactory(CharacterRepository(), getKeys())).
        get(CharacterViewModel::class.java)

        characterViewModel.characterResponse.observe(viewLifecycleOwner, { response ->
            when(response) {
                is Resource.Success -> {
                    binding.progressCircular.visibility = View.INVISIBLE
                    binding.recyclerViewCharacters.visibility = View.VISIBLE

                    binding.recyclerViewCharacters.layoutManager = LinearLayoutManager(context)
                    binding.recyclerViewCharacters.adapter = CharacterAdapter(response.data!!.data, this, requireContext())
                }

                is Resource.Error -> {
                    println(response.message)
                }

                is Resource.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
            }
        })

        characterViewModel.getCharacters()
    }

    fun openCharacterDetails(character: MarvelResult) {
        detailsViewModel.setDetails(character)
        Navigation.findNavController(requireView()).navigate(R.id.characterToDetails)
    }

    fun getKeys(): Map<String, String> {
        return mapOf(
            "PUBLIC_KEY" to requireContext().getString(R.string.PUBLIC_KEY),
            "PRIVATE_KEY" to requireContext().getString(R.string.PRIVATE_KEY),
        )
    }
}