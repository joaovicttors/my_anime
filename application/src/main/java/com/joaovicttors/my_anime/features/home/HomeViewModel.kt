package com.joaovicttors.my_anime.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response
import com.joaovicttors.domain.usecase.MarkAnimeAsFavoriteUseCase
import com.joaovicttors.domain.usecase.RetrieveAnimeListUseCase
import com.joaovicttors.my_anime.core.bases.BaseViewModel

class HomeViewModel(
    private val markAnimeAsFavoriteUseCase: MarkAnimeAsFavoriteUseCase,
    private val retrieveAnimeListUseCase: RetrieveAnimeListUseCase
) : BaseViewModel() {

    val retrieveAnimeListSuccess: LiveData<List<Anime>> get() = _retrieveAnimeListSuccess
    private val _retrieveAnimeListSuccess: MutableLiveData<List<Anime>> = MutableLiveData()

    val markAsFavoriteSuccess: LiveData<Unit> get() = _markAsFavoriteSuccess
    private val _markAsFavoriteSuccess: MutableLiveData<Unit> = MutableLiveData()

    fun markAnimeAsFavorite(anime: Anime) {
        launchData { ->
            markAnimeAsFavoriteUseCase(anime).also { response ->
                when (response) {
                    is Response.Error -> _error.value = response.message
                    is Response.Success -> _markAsFavoriteSuccess.value = response.body
                }
            }
        }
    }

    fun retrieveAnimeList() {
        launchData { ->
            retrieveAnimeListUseCase().also { response ->
                when (response) {
                    is Response.Error -> _error.value = response.message
                    is Response.Success -> _retrieveAnimeListSuccess.value = response.body
                }
            }
        }
    }
}