package com.joaovicttors.domain.entities

import com.joaovicttors.domain.aggregate.AnimeFormat
import com.joaovicttors.domain.aggregate.AnimeStatus

data class Anime(
    val id: Int,
    val aniListId: Int?,
    val malId: Int?,
    val tmdbId: Int?,
    val format: AnimeFormat,
    val status: AnimeStatus,
    val titles: Map<String, String>,
    val descriptions: Map<String, String>,
    val startDate: String?,
    val endDate: String?,
    val weeklyAiringDay: Int?,
    val seasonPeriod: Int,
    val seasonYear: Int?,
    val episodesCount: Int,
    val episodeDuration: Int?,
    val trailerUrl: String?,
    val coverImage: String,
    val hasCoverImage: Boolean,
    val coverColor: String?,
    val bannerImage: String?,
    val genres: List<String>,
    val sagas: List<Saga>?,
    val sequel: Int?,
    val prequel: Int?,
    val score: Float,
    val nsfw: Boolean,
    val recommendations: List<Int>?,
    var favorite: Boolean = false,
) {

    val title: String? get() = titles["en"] ?: titles["rj"]

    val description: String? get() = descriptions["en"]?.replace("<.*?>".toRegex(), "")

    val scoreFormatted: Float get() = (score * 5) / 100
}
