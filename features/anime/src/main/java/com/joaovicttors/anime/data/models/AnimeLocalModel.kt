package com.joaovicttors.anime.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joaovicttors.base.BaseModel

@Entity(tableName = "anime")
data class AnimeLocalModel(
    @PrimaryKey @ColumnInfo(name = ID) val id: Int,
    @ColumnInfo(name = FORMAT) val format: Int,
    @ColumnInfo(name = STATUS) val status: Int,
    @ColumnInfo(name = TITLES) val titles: Map<String, String>,
    @ColumnInfo(name = DESCRIPTIONS) val descriptions: Map<String, String>,
    @ColumnInfo(name = COVER_IMAGE) val coverImage: String,
    @ColumnInfo(name = SCORE) val score: Float,
) : BaseModel() {

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