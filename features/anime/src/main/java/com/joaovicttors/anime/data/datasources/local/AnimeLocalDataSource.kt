package com.joaovicttors.anime.data.datasources.local

import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.base.Response

interface AnimeLocalDataSource {

    suspend fun getAnimeList(): Response<List<Anime>>

    suspend fun insertAnimeList(animeList: List<Anime>)
}