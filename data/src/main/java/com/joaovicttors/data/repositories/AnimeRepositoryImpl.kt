package com.joaovicttors.data.repositories

import com.joaovicttors.data.sources.AnimeRemoteDataSource
import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response
import com.joaovicttors.domain.repositories.AnimeRepository

class AnimeRepositoryImpl(
    private val remoteDataSource: AnimeRemoteDataSource
) : AnimeRepository {

    override suspend fun retrieveRandomAnime(): Response<List<Anime>> {
        return remoteDataSource.retrieveRandomAnime()
    }
}