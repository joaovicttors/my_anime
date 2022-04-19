package com.joaovicttors.infrastructure.models

import com.google.gson.annotations.SerializedName

data class AnimeRemoteModel(
    @SerializedName(ID) val id: Int,
    @SerializedName(ANILIST_ID) val aniListId: Int?,
    @SerializedName(MAL_ID) val malId: Int?,
    @SerializedName(TMDB_ID) val tmdbId: Int?,
    @SerializedName(FORMAT) val format: Int?,
    @SerializedName(STATUS) val status: Int?,
    @SerializedName(TITLES) val titles: Map<String, String>?,
    @SerializedName(DESCRIPTIONS) val descriptions: Map<String, String>?,
    @SerializedName(START_DATE) val startDate: String?,
    @SerializedName(END_DATE) val endDate: String?,
    @SerializedName(WEEKLY_AIRING_DAY) val weeklyAiringDay: Int?,
    @SerializedName(SEASON_PERIOD) val seasonPeriod: Int?,
    @SerializedName(SEASON_YEAR) val seasonYear: Int?,
    @SerializedName(EPISODES_COUNT) val episodesCount: Int?,
    @SerializedName(EPISODE_DURATION) val episodeDuration: Int?,
    @SerializedName(TRAILER_URL) val trailerUrl: String?,
    @SerializedName(COVER_IMAGE) val coverImage: String?,
    @SerializedName(HAS_COVER_IMAGE) val hasCoverImage: Boolean?,
    @SerializedName(COVER_COLOR) val coverColor: String?,
    @SerializedName(BANNER_IMAGE) val bannerImage: String?,
    @SerializedName(GENRES) val genres: List<String>?,
    @SerializedName(SEQUEL) val sequel: Int?,
    @SerializedName(PREQUEL) val prequel: Int?,
    @SerializedName(SCORE) val score: Float?,
    @SerializedName(NSFW) val nsfw: Boolean?,
    @SerializedName(RECOMMENDATIONS) val recommendations: List<Int>?
) {
    private companion object {
        private  const val ID: String = "id"
        private  const val ANILIST_ID: String = "anilist_id"
        private  const val MAL_ID: String = "mal_id"
        private  const val TMDB_ID: String = "tmdb_id"
        private  const val FORMAT: String = "format"
        private  const val STATUS: String = "status"
        private  const val TITLES: String = "titles"
        private  const val DESCRIPTIONS: String = "descriptions"
        private  const val START_DATE: String = "start_date"
        private  const val END_DATE: String = "end_date"
        private  const val WEEKLY_AIRING_DAY: String = "weekly_airing_day"
        private  const val SEASON_PERIOD: String = "season_period"
        private  const val SEASON_YEAR: String = "season_year"
        private  const val EPISODES_COUNT: String = "episodes_count"
        private  const val EPISODE_DURATION: String = "episode_duration"
        private  const val TRAILER_URL: String = "trailer_url"
        private  const val COVER_IMAGE: String = "cover_image"
        private  const val HAS_COVER_IMAGE: String = "has_cover_image"
        private  const val COVER_COLOR: String = "cover_color"
        private  const val BANNER_IMAGE: String = "banner_image"
        private  const val GENRES: String = "genres"
        private  const val SEQUEL: String = "sequel"
        private  const val PREQUEL: String = "prequel"
        private  const val SCORE: String = "score"
        private  const val NSFW: String = "nsfw"
        private  const val RECOMMENDATIONS: String = "recommendations"
    }
}