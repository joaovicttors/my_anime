package com.joaovicttors.data.sources.remote.service

import com.joaovicttors.data.sources.remote.models.AnimeListResponse
import com.joaovicttors.data.sources.remote.models.SpecificAnimeResponse

interface AnimeRemoteService  {

    suspend fun retrieveAnimeListAsync(page: Int) : AnimeListResponse

    suspend fun retrieveSpecificAnimeAsync(id: Int) : SpecificAnimeResponse
}