package com.joaovicttors.infrastructure.services.retrofit

import com.joaovicttors.infrastructure.models.RandomAnimeModel
import com.joaovicttors.infrastructure.services.AnimeRemoteService
import retrofit2.http.GET

interface AnimeRetrofitService : AnimeRemoteService {

    @GET(RETRIEVE_RANDOM_ANIME)
    override suspend fun retrieveRandomAnimeAsync() : RandomAnimeModel

    private companion object {
        private const val RETRIEVE_RANDOM_ANIME: String = "v1/random/anime/5/true"
    }
}