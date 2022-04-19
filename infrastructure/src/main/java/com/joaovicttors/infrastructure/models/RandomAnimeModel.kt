package com.joaovicttors.infrastructure.models

import com.google.gson.annotations.SerializedName

data class RandomAnimeModel(
    @SerializedName(STATUS_CODE) val statusCode: Int?,
    @SerializedName(MESSAGE) val message: String?,
    @SerializedName(DATA) val data: List<AnimeRemoteModel>
) {
    private companion object {
        private  const val STATUS_CODE: String = "status_code"
        private  const val MESSAGE: String = "message"
        private  const val DATA: String = "data"
    }
}