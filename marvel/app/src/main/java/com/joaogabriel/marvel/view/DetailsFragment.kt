package com.joaogabriel.marvel.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.joaogabriel.marvel.R
import com.joaogabriel.marvel.databinding.FragmentCharacterDetailsBinding
import com.joaogabriel.marvel.utils.CharacterUtils
import com.joaogabriel.marvel.viewmodel.DetailsViewModel

class DetailsFragment : Fragment(R.layout.fragment_character_details) {
    private lateinit var binding: FragmentCharacterDetailsBinding
    private val detailsViewModel: DetailsViewModel by activityViewModels()
    private val characterUtils = CharacterUtils()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCharacterDetailsBinding.bind(view)

        detailsViewModel.detailsResponse.value?.let {
            binding.recyclerViewComics.layoutManager = LinearLayoutManager(context)
            binding.recyclerViewComics.adapter = DetailsAdapter(it)

            binding.toolbarDetails.title = it.name
            binding.txtCharacterDescription.text = it.description
            characterUtils.setCharacterImage(requireContext(), it.thumbnail.getPathExtension(), binding.imgCharacter)
        }

        toolbarDetails()
    }

    private fun toolbarDetails() {
        val toolbar = binding.toolbarDetails
        toolbar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.detailsToCharacter)
        }
    }


}