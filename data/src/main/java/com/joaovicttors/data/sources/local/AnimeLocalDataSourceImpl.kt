package com.joaovicttors.data.sources.local

import com.joaovicttors.data.mappers.AnimeEntityMapper
import com.joaovicttors.data.sources.local.service.AnimeLocalService
import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response

class AnimeLocalDataSourceImpl(
    private val localService: AnimeLocalService,
    private val entityMapper: AnimeEntityMapper,
) : AnimeLocalDataSource {

    override suspend fun deleteFavoriteAnime(anime: Anime): Response<Unit> {
        return try {
            localService.deleteFavoriteAnime(entityMapper.mapFromDomainEntity(anime)).let { _ ->
                Response.Success(Unit)
            }
        } catch (error: Exception) {
            Response.Error(error.message)
        }
    }

    override suspend fun insertFavoriteAnime(anime: Anime): Response<Unit> {
        return try {
            localService.insertFavoriteAnime(entityMapper.mapFromDomainEntity(anime)).let { _ ->
                Response.Success(Unit)
            }
        } catch (error: Exception) {
            Response.Error(error.message)
        }
    }

    override suspend fun existAnimeById(animeId: Int): Response<Boolean> {
        return try {
            localService.existAnimeById(animeId).let { response ->
                Response.Success(response)
            }
        } catch (error: Exception) {
            Response.Error(error.message)
        }
    }
}