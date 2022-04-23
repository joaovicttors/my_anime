package com.joaovicttors.infrastructure.services

import androidx.room.*
import com.joaovicttors.data.sources.local.models.AnimeEntity
import com.joaovicttors.data.sources.local.service.AnimeLocalService

@Dao
interface AnimeDaoService : AnimeLocalService {

    @Delete
    override suspend fun deleteFavoriteAnime(anime: AnimeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertFavoriteAnime(anime: AnimeEntity)

    @Query("SELECT EXISTS (SELECT 1 FROM anime WHERE id = :animeId)")
    override suspend fun existAnimeById(animeId: Int): Boolean
}