package com.joaovicttors.data.repositories

import com.joaovicttors.data.sources.local.AnimeLocalDataSource
import com.joaovicttors.data.sources.remote.AnimeRemoteDataSource
import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response
import com.joaovicttors.domain.repositories.AnimeRepository

class AnimeRepositoryImpl(
    private val localDatSource: AnimeLocalDataSource,
    private val remoteDataSource: AnimeRemoteDataSource
) : AnimeRepository {

    override suspend fun deleteAnime(anime: Anime): Response<Unit> {
        return localDatSource.deleteFavoriteAnime(anime)
    }

    override suspend fun insertAnime(anime: Anime): Response<Unit> {
        return localDatSource.insertFavoriteAnime(anime)
    }

    override suspend fun existAnimeById(animeId: Int): Response<Boolean> {
        return localDatSource.existAnimeById(animeId)
    }

    override suspend fun retrieveAnimeList(): Response<List<Anime>> {
        return remoteDataSource.retrieveAnimeList()
    }

    override suspend fun retrieveSpecificAnime(id: Int): Response<Anime> {
        return remoteDataSource.retrieveSpecificAnime(id)
    }
}