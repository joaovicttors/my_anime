package com.joaovicttors.data.sources.local.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.joaovicttors.data.models.AnimeModel

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey @ColumnInfo(name = ID) override val id: Int,
    @ColumnInfo(name = FORMAT) override val format: Int,
    @ColumnInfo(name = STATUS) override val status: Int,
    @ColumnInfo(name = TITLES) override val titles: Map<String, String>,
    @ColumnInfo(name = DESCRIPTIONS) override val descriptions: Map<String, String>,
    @ColumnInfo(name = COVER_IMAGE) override val coverImage: String,
    @ColumnInfo(name = SCORE) override val score: Float,
) : AnimeModel() {

    private companion object {
        private const val ID: String = "id"
        private const val FORMAT: String = "format"
        private const val STATUS: String = "status"
        private const val TITLES: String = "titles"
        private const val DESCRIPTIONS: String = "descriptions"
        private const val COVER_IMAGE: String = "cover_image"
        private const val SCORE: String = "score"
    }
}