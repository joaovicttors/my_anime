package com.joaovicttors.infrastructure.services

import com.joaovicttors.data.sources.remote.models.AnimeListResponse
import com.joaovicttors.data.sources.remote.models.SpecificAnimeResponse
import com.joaovicttors.data.sources.remote.service.AnimeRemoteService
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeRetrofitService : AnimeRemoteService {

    @GET(RETRIEVE_ANIME_LIST)
    override suspend fun retrieveAnimeListAsync(@Query(value = "page") page: Int): AnimeListResponse

    @GET(RETRIEVE_SPECIFIC_ANIME)
    override suspend fun retrieveSpecificAnimeAsync(@Path(value = "id") id: Int): SpecificAnimeResponse

    private companion object {
        private const val RETRIEVE_ANIME_LIST: String = "/v1/anime?per_page=10"
        private const val RETRIEVE_SPECIFIC_ANIME: String = "/v1/anime/{id}"
    }
}