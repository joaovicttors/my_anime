package com.joaovicttors.anime.domain.enums

enum class AnimeStatus {
    FINISHED, RELEASING, NOT_YET_RELEASE, CANCELLED;

    companion object {

        fun value(index: Int): AnimeStatus = values()[index]
    }
}