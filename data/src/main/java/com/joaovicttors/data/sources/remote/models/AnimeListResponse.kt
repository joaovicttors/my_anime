package com.joaovicttors.data.sources.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeListResponse(
    @Json(name = STATUS_CODE) val statusCode: Int,
    @Json(name = MESSAGE) val message: String,
    @Json(name = DATA) val data: Data
) {

    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = CURRENT_PAGE) val currentPage: Int,
        @Json(name = COUNT) val count: Int,
        @Json(name = DOCUMENTS) val documents: List<AnimeResponse>,
        @Json(name = LAST_PAGE) val lastPage: Int,
    ) {

        private companion object {
            private const val CURRENT_PAGE: String = "current_page"
            private const val COUNT: String = "count"
            private const val DOCUMENTS: String = "documents"
            private const val LAST_PAGE: String = "last_page"
        }
    }

    private companion object {
        private const val STATUS_CODE: String = "status_code"
        private const val MESSAGE: String = "message"
        private const val DATA: String = "data"
    }
}