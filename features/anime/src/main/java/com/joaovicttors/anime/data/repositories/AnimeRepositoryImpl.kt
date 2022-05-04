package com.joaovicttors.anime.data.repositories

import com.joaovicttors.anime.data.datasources.local.AnimeLocalDataSource
import com.joaovicttors.anime.data.datasources.remote.AnimeRemoteDataSource
import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.anime.domain.repositories.AnimeRepository
import com.joaovicttors.base.Response

class AnimeRepositoryImpl(
    private val localDataSource: AnimeLocalDataSource,
    private val remoteDataSource: AnimeRemoteDataSource
) : AnimeRepository {

    override suspend fun getAnimeListFromLocalStorage(): Response<List<Anime>> {
        return localDataSource.getAnimeList()
    }

    override suspend fun getAnimeListFromRemoteStorage(): Response<List<Anime>> {
        return remoteDataSource.getAnimeList()
    }

    override suspend fun insertAnimeListOnLocalStorage(animeList: List<Anime>) {
        return localDataSource.insertAnimeList(animeList)
    }
}