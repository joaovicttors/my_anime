package com.joaovicttors.domain.aggregate

enum class AnimeFormat {
    TV, TV_SHORT, MOVIE, SPECIAL, OVA, ONA, MUSIC;

    companion object {
        fun getByValue(value: Int) : AnimeFormat {
            return AnimeFormat.values()[value]
        }
    }
}