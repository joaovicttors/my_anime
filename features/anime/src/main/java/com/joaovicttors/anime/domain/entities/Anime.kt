package com.joaovicttors.anime.domain.entities

import android.os.Parcelable
import com.joaovicttors.anime.domain.enums.AnimeFormat
import com.joaovicttors.anime.domain.enums.AnimeStatus
import com.joaovicttors.base.BaseEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    val id: Int,
    val format: AnimeFormat,
    val status: AnimeStatus,
    val titles: Map<String, String>,
    val descriptions: Map<String, String>,
    val coverImage: String,
    val score: Float
) : BaseEntity(), Parcelable {

    val title: String? get() = titles["en"]

    val description: String? get() = descriptions["en"]?.replace("<.*?>".toRegex(), "")

    val formattedScore: Float get() = (score * 5) / 100
}
