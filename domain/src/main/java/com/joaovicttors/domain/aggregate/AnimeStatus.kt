package com.joaovicttors.domain.aggregate

enum class AnimeStatus {
    FINISHED, RELEASING, NOT_YET_RELEASE, CANCELLED;

    companion object {
        fun getByValue(value: Int) : AnimeStatus {
            return AnimeStatus.values()[value]
        }
    }
}