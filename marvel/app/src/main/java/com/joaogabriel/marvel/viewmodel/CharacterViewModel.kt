package com.joaogabriel.marvel.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joaogabriel.marvel.model.MarvelData
import com.joaogabriel.marvel.model.repository.CharacterRepository
import com.joaogabriel.marvel.utils.MarvelHashGenerate
import com.joaogabriel.marvel.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await
import java.util.concurrent.TimeUnit

class CharacterViewModel(private val characterRepository: CharacterRepository, keys: Map<String, String>) : ViewModel() {
    val characterResponse: MutableLiveData<Resource<MarvelData>> = MutableLiveData()

    private val publicKey = keys["PUBLIC_KEY"]
    private val privateKey = keys["PRIVATE_KEY"]
    private val timestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
    private val hash = MarvelHashGenerate.generate(timestamp, privateKey!!, publicKey!!)

    fun getCharacters() {
        characterResponse.postValue(Resource.Loading())

        CoroutineScope(Dispatchers.IO).launch {
            val request = characterRepository.makeRequest()

            try {
                val response = request.getCharacters(publicKey!!, timestamp.toString(), hash).await()

                characterResponse.postValue(Resource.Success(response))
            } catch (exception: Exception) {
                characterResponse.postValue(Resource.Error(null, exception.message))
            }
        }
    }

    class CharacterViewModelFactory(private val characterRepository : CharacterRepository, private val keys: Map<String, String>)
        : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CharacterViewModel(characterRepository, keys) as T
        }
    }
}