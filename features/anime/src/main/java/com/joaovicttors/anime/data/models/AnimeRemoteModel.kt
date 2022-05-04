package com.joaovicttors.anime.data.models

import com.google.gson.annotations.SerializedName
import com.joaovicttors.base.BaseModel

data class AnimeRemoteModel(
    @SerializedName(STATUS_CODE) val statusCode: Int,
    @SerializedName(MESSAGE) val message: String,
    @SerializedName(DATA) val data: Data
) : BaseModel() {


    data class Data(
        @SerializedName(CURRENT_PAGE) val currentPage: Int,
        @SerializedName(COUNT) val count: Int,
        @SerializedName(DOCUMENTS) val documents: List<Documents>,
        @SerializedName(LAST_PAGE) val lastPage: Int,
    ) : BaseModel() {

        private companion object {
            private const val CURRENT_PAGE: String = "current_page"
            private const val COUNT: String = "count"
            private const val DOCUMENTS: String = "documents"
            private const val LAST_PAGE: String = "last_page"
        }
    }

    data class Documents(
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

    private companion object {
        private const val STATUS_CODE: String = "status_code"
        private const val MESSAGE: String = "message"
        private const val DATA: String = "data"
    }
}