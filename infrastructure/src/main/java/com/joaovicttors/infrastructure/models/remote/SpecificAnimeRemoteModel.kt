package com.joaovicttors.infrastructure.models.remote

import com.joaovicttors.infrastructure.models.AnimeModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpecificAnimeRemoteModel(
    @Json(name = STATUS_CODE) val statusCode: Int?,
    @Json(name = MESSAGE) val message: String?,
    @Json(name = DATA) val data: AnimeModel
) {

    private companion object {
        private const val STATUS_CODE: String = "status_code"
        private const val MESSAGE: String = "message"
        private const val DATA: String = "data"
    }
}