package com.joaovicttors.anime.data.datasources.remote

import com.joaovicttors.anime.data.datasources.remote.service.RetrofitAnimeService
import com.joaovicttors.anime.data.mappers.AnimeRemoteModelMapper
import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.base.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AnimeRemoteDataSourceImpl(
    private val mapper: AnimeRemoteModelMapper,
    private val service: RetrofitAnimeService,
    private val dispatcher: CoroutineDispatcher
) : AnimeRemoteDataSource {

    override suspend fun getAnimeList(): Response<List<Anime>> {
        return try {
            withContext(dispatcher) { ->
                service.getAnimeList().let { data ->
                    data.map { mapper.mapToDomainEntity(it) }.let { mappedData ->
                        Response.Success(mappedData)
                    }
                }
            }
        } catch (error: Exception) {
            Response.Error(error.message)
        }
    }
}