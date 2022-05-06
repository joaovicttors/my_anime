package com.joaovicttors.anime.presentation.features.anime_list.view

import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.base.BaseViewState

data class AnimeListViewState(
    val data: List<Anime> = emptyList(),
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null
) : BaseViewState()