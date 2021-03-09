package com.joaogabriel.marvel.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joaogabriel.marvel.model.repository.CharacterRepository
import com.joaogabriel.marvel.utils.MarvelHashGenerate
import com.joaogabriel.marvel.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await
import java.util.concurrent.TimeUnit

class CharacterViewModel(private val characterRepository: CharacterRepository) : ViewModel() {
    val characterResponse: MutableLiveData<Resource<Any>> = MutableLiveData()

    private val publicKey = "a80c35879def43835e6144212fee11a7"
    private val privateKey = "a002ed86c355298a07af83e258a8ad55ce6dcd58"
    private val timestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
    private val hash = MarvelHashGenerate.generate(timestamp, privateKey, publicKey)

    fun getCharacters() {
        characterResponse.postValue(Resource.Loading())

        CoroutineScope(Dispatchers.IO).launch {
            val request = characterRepository.makeRequest()

            try {
                val response = request.getCharacters(publicKey, timestamp.toString(), hash).await()

                characterResponse.postValue(Resource.Success(response))
            } catch (exception: Exception) {
                characterResponse.postValue(Resource.Error(null, exception.message))
            }
        }
    }

    class CharacterViewModelFactory(private val characterRepository : CharacterRepository)
        : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CharacterViewModel(characterRepository) as T
        }
    }
}