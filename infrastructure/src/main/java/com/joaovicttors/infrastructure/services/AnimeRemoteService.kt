package com.joaovicttors.infrastructure.services

import com.joaovicttors.infrastructure.models.remote.AnimeListRemoteModel
import com.joaovicttors.infrastructure.models.remote.SpecificAnimeRemoteModel

interface AnimeRemoteService {
    suspend fun retrieveAnimeListAsync(currentPage: Int, perPage: Int = 10): AnimeListRemoteModel
    suspend fun retrieveSpecificAnimeAsync(id: Int): SpecificAnimeRemoteModel
}