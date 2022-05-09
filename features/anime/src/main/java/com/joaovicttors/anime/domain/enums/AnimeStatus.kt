package com.joaovicttors.anime.domain.enums

enum class AnimeStatus {
    UNKNOWN, FINISHED, RELEASING, NOT_YET_RELEASE, CANCELLED;

    val index get() = ordinal.minus(1)

    companion object {

        fun value(index: Int): AnimeStatus {
            return runCatching { values()[index + 1] }.getOrElse { values()[0] }
        }
    }
}