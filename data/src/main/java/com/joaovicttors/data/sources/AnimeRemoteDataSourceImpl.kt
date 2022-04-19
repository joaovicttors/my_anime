package com.joaovicttors.data.sources

import com.joaovicttors.data.mappers.AnimeRemoteModelMapper
import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response
import com.joaovicttors.infrastructure.services.AnimeRemoteService

class AnimeRemoteDataSourceImpl(
    private val animeRetrofitService: AnimeRemoteService
) : AnimeRemoteDataSource {

    override suspend fun retrieveRandomAnime(): Response<List<Anime>> {
        return try {
            animeRetrofitService.retrieveRandomAnimeAsync().let { response ->
                Response.Success(AnimeRemoteModelMapper.mapToEntity(response))
            }
        } catch (error: Exception) {
            Response.Error(error.message)
        }
    }
}