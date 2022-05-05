package com.joaovicttors.anime.data.datasources.remote.service

import com.joaovicttors.anime.data.models.AnimeRemoteModel
import retrofit2.http.GET

interface RetrofitAnimeService {

    @GET(ANIME_LIST_ENDPOINT)
    suspend fun getAnimeList(): AnimeRemoteModel

    companion object {
        const val ANIME_LIST_ENDPOINT: String = "v1/anime"
    }
}