package com.joaovicttors.domain.entities

import com.joaovicttors.domain.aggregate.AnimeFormat
import com.joaovicttors.domain.aggregate.AnimeStatus

data class Anime(
    val id: Int,
    val format: AnimeFormat,
    val status: AnimeStatus,
    val titles: Map<String, String>,
    val descriptions: Map<String, String>,
    val coverImage: String,
    val score: Float,
    var favorite: Boolean = false,
) {

    val title: String? get() = titles["en"] ?: titles["rj"]

    val description: String? get() = descriptions["en"]?.replace("<.*?>".toRegex(), "")

    val scoreFormatted: Float get() = (score * 5) / 100
}
