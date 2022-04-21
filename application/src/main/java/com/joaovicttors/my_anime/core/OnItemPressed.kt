package com.joaovicttors.my_anime.core

import com.joaovicttors.domain.entities.Anime

// TODO ajeitar isso
interface RecyclerViewInterface {
    fun onItemPressed(anime: Anime)
    fun onFavoritePressed(anime: Anime)
}