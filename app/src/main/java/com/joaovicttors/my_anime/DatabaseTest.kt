package com.joaovicttors.my_anime

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joaovicttors.anime.data.datasources.local.service.RoomAnimeService
import com.joaovicttors.anime.data.models.AnimeLocalModel

// TODO joao.santana
@Database(version = 1, entities = [AnimeLocalModel::class])
abstract class DatabaseTest : RoomDatabase() {

    abstract fun roomAnimeService(): RoomAnimeService
}