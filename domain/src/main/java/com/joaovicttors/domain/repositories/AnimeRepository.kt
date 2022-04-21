package com.joaovicttors.domain.repositories

import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response

interface AnimeRepository {
    suspend fun retrieveAnimeList(): Response<List<Anime>>
    suspend fun retrieveSpecificAnime(id: Int): Response<Anime>
}