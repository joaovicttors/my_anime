package com.joaovicttors.data.sources.remote.models

import com.joaovicttors.data.models.AnimeModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeResponse(
    @Json(name = ID) override val id: Int,
    @Json(name = FORMAT) override val format: Int,
    @Json(name = STATUS) override val status: Int,
    @Json(name = TITLES) override val titles: Map<String, String>,
    @Json(name = DESCRIPTIONS) override val descriptions: Map<String, String>,
    @Json(name = COVER_IMAGE) override val coverImage: String,
    @Json(name = SCORE) override val score: Float
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