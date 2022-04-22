package com.joaovicttors.data.sources.local.service

import com.joaovicttors.data.sources.local.models.AnimeEntity

interface AnimeLocalService {
    
    suspend fun deleteFavoriteAnime(anime: AnimeEntity)

    suspend fun insertFavoriteAnime(anime: AnimeEntity)

    suspend fun existAnimeById(animeId: Int): Boolean
}