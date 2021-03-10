package com.joaogabriel.marvel.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joaogabriel.marvel.model.Character

class DetailsViewModel : ViewModel() {
    val detailsResponse: MutableLiveData<Character> = MutableLiveData()

    fun setDetails(character: Character) {
        detailsResponse.value = character
    }
}