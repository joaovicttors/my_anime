package com.joaovicttors.anime.domain.enums

enum class AnimeFormat {
    TV, TV_SHORT, MOVIE, SPECIAL, OVA, ONA, MUSIC;

    companion object {

        fun value(index: Int): AnimeFormat = values()[index]
    }
}