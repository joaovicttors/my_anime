package com.joaovicttors.data.sources.remote

import com.joaovicttors.data.mappers.AnimeResponseMapper
import com.joaovicttors.data.sources.remote.service.AnimeRemoteService
import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response

class AnimeRemoteDataSourceImpl(
    private val remoteService: AnimeRemoteService,
    private val responseMapper: AnimeResponseMapper
) : AnimeRemoteDataSource {

    private var currentPage: Int = 1;

    override suspend fun retrieveAnimeList(): Response<List<Anime>> {
        return try {
            remoteService.retrieveAnimeListAsync(currentPage).let { response ->
                Response.Success(response.data.documents.map { responseMapper.mapToDomainEntity(it) })
            }
        } catch (error: Exception) {
            Response.Error(error.message)
        } finally {
            currentPage++
        }
    }

    override suspend fun retrieveSpecificAnime(id: Int): Response<Anime> {
        return try {
            remoteService.retrieveSpecificAnimeAsync(id).let { response ->
                Response.Success(responseMapper.mapToDomainEntity(response.data))
            }
        } catch (error: Exception) {
            Response.Error(error.message)
        }
    }
}