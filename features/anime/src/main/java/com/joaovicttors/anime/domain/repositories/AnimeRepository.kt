package com.joaovicttors.anime.domain.repositories

import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.base.Response

interface AnimeRepository {

    suspend fun getAnimeListFromLocalStorage(): Response<List<Anime>>

    suspend fun getAnimeListFromRemoteStorage(): Response<List<Anime>>

    suspend fun insertAnimeListOnLocalStorage(animeList: List<Anime>)
}