package com.joaovicttors.anime.presentation.features.anime_list

import androidx.lifecycle.viewModelScope
import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.base.BaseViewModel
import com.joaovicttors.base.Response
import com.joaovicttors.base.usecase.BaseUseCase
import com.joaovicttors.base.usecase.utilities.NoParam
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimeListViewModel(
    private val getAnimeListUseCase: BaseUseCase<NoParam, List<Anime>>
) : BaseViewModel() {

    override val viewState: StateFlow<AnimeListViewState> get() = _viewState
    private val _viewState: MutableStateFlow<AnimeListViewState> = MutableStateFlow(AnimeListViewState())

    fun getAnimeList() {
        viewModelScope.launch(Dispatchers.Main) { ->
            try {
                _viewState.update { it.copy(isLoading = true) }

                getAnimeListUseCase(NoParam()).also { response: Response<List<Anime>> ->
                    when (response) {
                        is Response.Error -> _viewState.update { it.copy(errorMessage = response.message) }
                        is Response.Success -> _viewState.update { it.copy(data = response.data) }
                    }
                }
            } catch (error: Exception) {
                _viewState.update { it.copy(errorMessage = error.message) }
            } finally {
                _viewState.update { it.copy(isLoading = false) }
            }
        }
    }
}