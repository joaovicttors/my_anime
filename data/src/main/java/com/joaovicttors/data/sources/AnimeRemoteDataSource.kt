package com.joaovicttors.data.sources

import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response

interface AnimeRemoteDataSource {
    suspend fun retrieveRandomAnime(): Response<List<Anime>>
}