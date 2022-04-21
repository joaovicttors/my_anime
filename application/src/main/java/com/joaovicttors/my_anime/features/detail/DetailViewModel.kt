package com.joaovicttors.my_anime.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response
import com.joaovicttors.domain.repositories.AnimeRepository
import com.joaovicttors.my_anime.core.bases.BaseViewModel

class DetailViewModel(
    private val animeRepository: AnimeRepository
) : BaseViewModel() {

    val success: LiveData<Anime> get() = _success
    private val _success: MutableLiveData<Anime> = MutableLiveData()

    fun retrieveSpecificAnime(id: Int) {
        launchData { ->
            animeRepository.retrieveSpecificAnime(id).also { response ->
                when (response) {
                    is Response.Error -> _error.value = response.message
                    is Response.Success -> _success.value = response.body
                }
            }
        }
    }
}