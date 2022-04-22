package com.joaovicttors.domain.repositories

import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response

interface AnimeRepository {

    suspend fun deleteAnime(anime: Anime): Response<Unit>

    suspend fun insertAnime(anime: Anime): Response<Unit>

    suspend fun existAnimeById(animeId: Int): Response<Boolean>

    suspend fun retrieveAnimeList(): Response<List<Anime>>

    suspend fun retrieveSpecificAnime(id: Int): Response<Anime>
}