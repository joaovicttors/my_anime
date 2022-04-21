package com.joaovicttors.data.sources

import com.joaovicttors.data.mappers.AnimeMapper
import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response
import com.joaovicttors.infrastructure.services.AnimeRemoteService

class AnimeRemoteDataSourceImpl(
    private val animeRetrofitService: AnimeRemoteService
) : AnimeRemoteDataSource {

    private var currentPage: Int = 1;

    override suspend fun retrieveAnimeList(): Response<List<Anime>> {
        return try {
            animeRetrofitService.retrieveAnimeListAsync(currentPage).let { response ->
                Response.Success(response.data.documents.map { AnimeMapper.modelToEntity(it) })
            }
        } catch (error: Exception) {
            Response.Error(error.message)
        } finally {
            currentPage++
        }
    }

    override suspend fun retrieveSpecificAnime(id: Int): Response<Anime> {
        return try {
            animeRetrofitService.retrieveSpecificAnimeAsync(id).let { response ->
                Response.Success(AnimeMapper.modelToEntity(response.data))
            }
        } catch (error: Exception) {
            Response.Error(error.message)
        }
    }
}