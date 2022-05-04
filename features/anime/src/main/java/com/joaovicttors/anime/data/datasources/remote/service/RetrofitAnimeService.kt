package com.joaovicttors.anime.data.datasources.remote.service

import com.joaovicttors.anime.data.models.AnimeRemoteModel

interface RetrofitAnimeService {

    suspend fun getAnimeList(): List<AnimeRemoteModel>
}