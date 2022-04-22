package com.joaovicttors.data.sources.local

import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response

interface AnimeLocalDataSource {

    suspend fun deleteFavoriteAnime(anime: Anime): Response<Unit>

    suspend fun insertFavoriteAnime(anime: Anime): Response<Unit>

    suspend fun existAnimeById(animeId: Int): Response<Boolean>
}