package com.joaovicttors.anime.domain.enums

enum class AnimeFormat {
    UNKNOWN, TV, TV_SHORT, MOVIE, SPECIAL, OVA, ONA, MUSIC;

    companion object {

        fun value(index: Int): AnimeFormat {
            return runCatching { values()[index + 1] }.getOrElse { values()[0] }
        }
    }
}