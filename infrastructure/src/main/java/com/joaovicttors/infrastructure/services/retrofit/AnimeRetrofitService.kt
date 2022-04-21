package com.joaovicttors.infrastructure.services.retrofit

import com.joaovicttors.infrastructure.models.remote.AnimeListRemoteModel
import com.joaovicttors.infrastructure.models.remote.SpecificAnimeRemoteModel
import com.joaovicttors.infrastructure.services.AnimeRemoteService
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeRetrofitService : AnimeRemoteService {

    @GET(RETRIEVE_ANIME)
    override suspend fun retrieveAnimeListAsync(
        @Query("page") currentPage: Int,
        @Query("per_page") perPage: Int,
    ) : AnimeListRemoteModel

    @GET(RETRIEVE_ANIME.plus("/{id}"))
    override suspend fun retrieveSpecificAnimeAsync(
        @Path("id") id: Int
    ): SpecificAnimeRemoteModel

    private companion object {
        private const val RETRIEVE_ANIME: String = "/v1/anime"
    }
}