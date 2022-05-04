package com.joaovicttors.anime.data.datasources.remote

import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.base.Response

interface AnimeRemoteDataSource {

    suspend fun getAnimeList(): Response<List<Anime>>
}