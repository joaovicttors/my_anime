package com.joaovicttors.anime.domain.enums

enum class AnimeStatus {
    UNKNOWN, FINISHED, RELEASING, NOT_YET_RELEASE, CANCELLED;

    companion object {

        fun value(index: Int): AnimeStatus {
            return runCatching { values()[index + 1] }.getOrElse { values()[0] }
        }
    }
}