package com.joaovicttors.anime.data.models.remote

import com.google.gson.annotations.SerializedName
import com.joaovicttors.base.BaseModel

data class AnimeRemoteModel(
    @SerializedName(ID) val id: Int,
    @SerializedName(FORMAT) val format: Int,
    @SerializedName(STATUS) val status: Int,
    @SerializedName(TITLES) val titles: Map<String, String>,
    @SerializedName(DESCRIPTIONS) val descriptions: Map<String, String>,
    @SerializedName(COVER_IMAGE) val coverImage: String,
    @SerializedName(SCORE) val score: Float
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