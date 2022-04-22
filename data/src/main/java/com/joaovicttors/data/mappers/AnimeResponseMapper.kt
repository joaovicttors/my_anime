package com.joaovicttors.data.mappers

import com.joaovicttors.data.sources.remote.models.AnimeResponse
import com.joaovicttors.domain.aggregate.AnimeFormat
import com.joaovicttors.domain.aggregate.AnimeStatus
import com.joaovicttors.domain.entities.Anime

class AnimeResponseMapper {

    fun mapToDomainEntity(response: AnimeResponse) : Anime {
        return Anime(
            response.id,
            AnimeFormat.getByValue(response.format),
            AnimeStatus.getByValue(response.status),
            response.titles,
            response.descriptions,
            response.coverImage,
            response.score
        )
    }
}