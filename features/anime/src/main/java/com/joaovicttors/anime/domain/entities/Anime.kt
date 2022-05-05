package com.joaovicttors.anime.domain.entities

import com.joaovicttors.anime.domain.enums.AnimeFormat
import com.joaovicttors.anime.domain.enums.AnimeStatus
import com.joaovicttors.base.BaseEntity

data class Anime(
    val id: Int,
    val format: AnimeFormat,
    val status: AnimeStatus,
    val titles: Map<String, String>,
    val descriptions: Map<String, String>,
    val coverImage: String,
    val score: Float
) : BaseEntity()
