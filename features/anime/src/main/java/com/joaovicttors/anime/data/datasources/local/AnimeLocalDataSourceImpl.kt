package com.joaovicttors.anime.data.datasources.local

import com.joaovicttors.anime.data.datasources.local.service.RoomAnimeService
import com.joaovicttors.anime.data.mappers.AnimeLocalModelMapper
import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.base.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AnimeLocalDataSourceImpl(
    private val mapper: AnimeLocalModelMapper,
    private val service: RoomAnimeService,
    private val dispatcher: CoroutineDispatcher,
) : AnimeLocalDataSource {

    override suspend fun getAnimeList(): Response<List<Anime>> {
        return try {
            withContext(dispatcher) { ->
                service.getAll().let { data ->
                    data.map { mapper.mapToDomainEntity(it) }.let { mappedData ->
                        Response.Success(mappedData)
                    }
                }
            }
        } catch (error: Exception) {
            Response.Error(error.message)
        }
    }

    override suspend fun insertAnimeList(animeList: List<Anime>) {
        try {
            withContext(dispatcher) { ->
                animeList.map { mapper.mapFromDomainEntity(it) }.also { mappedData ->
                    service.insertAll(mappedData)
                }
            }
        } catch (error: Exception) {

        }
    }
}