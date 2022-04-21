package com.joaovicttors.infrastructure.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SagaModel(
    @Json(name = TITLES) val titles: Map<String, String>,
    @Json(name = DESCRIPTIONS) val descriptions: Map<String, String>,
    @Json(name = EPISODE_FROM) val episodeFrom: Int,
    @Json(name = EPISODE_TO) val episodeTo: Int,
    @Json(name = EPISODE_COUNT) val episodeCount: Int,
) {
    private companion object {
        private const val TITLES: String = "titles"
        private const val DESCRIPTIONS: String = "descriptions"
        private const val EPISODE_FROM: String = "episode_from"
        private const val EPISODE_TO: String = "episode_to"
        private const val EPISODE_COUNT: String = "episodes_count"
    }
}