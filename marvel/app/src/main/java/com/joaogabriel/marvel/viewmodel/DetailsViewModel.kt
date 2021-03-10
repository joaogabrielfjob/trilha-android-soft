package com.joaogabriel.marvel.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joaogabriel.marvel.model.MarvelResult

class DetailsViewModel : ViewModel() {
    val detailsResponse: MutableLiveData<MarvelResult> = MutableLiveData()

    fun setDetails(character: MarvelResult) {
        detailsResponse.value = character
    }
}