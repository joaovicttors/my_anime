package com.joaovicttors.anime.presentation.features.anime_detail

import com.joaovicttors.base.BaseViewState

data class AnimeDetailViewState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null
) : BaseViewState()