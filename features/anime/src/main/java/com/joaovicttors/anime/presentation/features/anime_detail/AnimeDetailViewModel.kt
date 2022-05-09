package com.joaovicttors.anime.presentation.features.anime_detail

import com.joaovicttors.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AnimeDetailViewModel : BaseViewModel() {

    override val viewState: StateFlow<AnimeDetailViewState> get() = _viewState
    private val _viewState: MutableStateFlow<AnimeDetailViewState> = MutableStateFlow(AnimeDetailViewState())

}