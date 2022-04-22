package com.joaovicttors.infrastructure.builders

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.joaovicttors.data.sources.local.models.AnimeEntity
import com.joaovicttors.infrastructure.converters.MapTypeConverter
import com.joaovicttors.infrastructure.services.AnimeDaoService

@Database(version = 1, entities = [AnimeEntity::class])
@TypeConverters(MapTypeConverter::class)
abstract class RoomBuilder : RoomDatabase() {

    abstract fun animeDao(): AnimeDaoService
}