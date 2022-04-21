package com.joaovicttors.my_anime.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response
import com.joaovicttors.domain.repositories.AnimeRepository
import com.joaovicttors.my_anime.core.bases.BaseViewModel

class HomeViewModel(
    private val animeRepository: AnimeRepository
) : BaseViewModel() {

    val success: LiveData<List<Anime>> get() = _success
    private val _success: MutableLiveData<List<Anime>> = MutableLiveData()

    fun retrieveAnimeList() {
        launchData { ->
            animeRepository.retrieveAnimeList().also { response ->
                when (response) {
                    is Response.Error -> _error.value = response.message
                    is Response.Success -> _success.value = response.body
                }
            }
        }
    }
}