package com.joaovicttors.my_anime.core

import com.joaovicttors.domain.entities.Anime

interface RecyclerViewInterface {
    fun onItemPressed(anime: Anime)
    fun onFavoritePressed(anime: Anime)
}