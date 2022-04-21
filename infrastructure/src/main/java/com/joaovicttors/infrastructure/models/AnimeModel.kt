package com.joaovicttors.infrastructure.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnimeModel(
    @Json(name = ID) val id: Int,
    @Json(name = ANILIST_ID) val aniListId: Int?,
    @Json(name = MAL_ID) val malId: Int?,
    @Json(name = TMDB_ID) val tmdbId: Int?,
    @Json(name = FORMAT) val format: Int,
    @Json(name = STATUS) val status: Int,
    @Json(name = TITLES) val titles: Map<String, String>,
    @Json(name = DESCRIPTIONS) val descriptions: Map<String, String>,
    @Json(name = START_DATE) val startDate: String?,
    @Json(name = END_DATE) val endDate: String?,
    @Json(name = WEEKLY_AIRING_DAY) val weeklyAiringDay: Int?,
    @Json(name = SEASON_PERIOD) val seasonPeriod: Int,
    @Json(name = SEASON_YEAR) val seasonYear: Int?,
    @Json(name = EPISODES_COUNT) val episodesCount: Int,
    @Json(name = EPISODE_DURATION) val episodeDuration: Int?,
    @Json(name = TRAILER_URL) val trailerUrl: String?,
    @Json(name = COVER_IMAGE) val coverImage: String,
    @Json(name = HAS_COVER_IMAGE) val hasCoverImage: Boolean,
    @Json(name = COVER_COLOR) val coverColor: String?,
    @Json(name = BANNER_IMAGE) val bannerImage: String?,
    @Json(name = GENRES) val genres: List<String>,
    @Json(name = SAGAS) val sagas: List<SagaModel>?,
    @Json(name = SEQUEL) val sequel: Int?,
    @Json(name = PREQUEL) val prequel: Int?,
    @Json(name = SCORE) val score: Float,
    @Json(name = NSFW) val nsfw: Boolean,
    @Json(name = RECOMMENDATIONS) val recommendations: List<Int>?
) {
    private companion object {
        private const val ID: String = "id"
        private const val ANILIST_ID: String = "anilist_id"
        private const val MAL_ID: String = "mal_id"
        private const val TMDB_ID: String = "tmdb_id"
        private const val FORMAT: String = "format"
        private const val STATUS: String = "status"
        private const val TITLES: String = "titles"
        private const val DESCRIPTIONS: String = "descriptions"
        private const val START_DATE: String = "start_date"
        private const val END_DATE: String = "end_date"
        private const val WEEKLY_AIRING_DAY: String = "weekly_airing_day"
        private const val SEASON_PERIOD: String = "season_period"
        private const val SEASON_YEAR: String = "season_year"
        private const val EPISODES_COUNT: String = "episodes_count"
        private const val EPISODE_DURATION: String = "episode_duration"
        private const val TRAILER_URL: String = "trailer_url"
        private const val COVER_IMAGE: String = "cover_image"
        private const val HAS_COVER_IMAGE: String = "has_cover_image"
        private const val COVER_COLOR: String = "cover_color"
        private const val BANNER_IMAGE: String = "banner_image"
        private const val GENRES: String = "genres"
        private const val SAGAS: String = "sagas"
        private const val SEQUEL: String = "sequel"
        private const val PREQUEL: String = "prequel"
        private const val SCORE: String = "score"
        private const val NSFW: String = "nsfw"
        private const val RECOMMENDATIONS: String = "recommendations"
    }
}