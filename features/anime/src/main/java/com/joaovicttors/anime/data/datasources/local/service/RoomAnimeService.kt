package com.joaovicttors.anime.data.datasources.local.service

import com.joaovicttors.anime.data.models.AnimeLocalModel

interface RoomAnimeService {

    suspend fun getAll(): List<AnimeLocalModel>

    suspend fun insertAll(animeList: List<AnimeLocalModel>)
}