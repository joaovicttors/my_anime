package com.joaovicttors.anime.data.datasources.local.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joaovicttors.anime.data.models.AnimeLocalModel

@Dao
interface RoomAnimeService {

    @Query(value = "SELECT * FROM anime")
    suspend fun getAll(): List<AnimeLocalModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(animeList: List<AnimeLocalModel>)
}