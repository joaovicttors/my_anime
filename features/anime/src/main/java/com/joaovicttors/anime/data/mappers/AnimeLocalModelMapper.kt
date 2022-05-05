package com.joaovicttors.anime.data.mappers

import com.joaovicttors.anime.data.models.AnimeLocalModel
import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.anime.domain.enums.AnimeFormat
import com.joaovicttors.anime.domain.enums.AnimeStatus
import com.joaovicttors.base.BaseMapper

class AnimeLocalModelMapper : BaseMapper<AnimeLocalModel, Anime>() {

    override fun mapToDomainEntity(data: AnimeLocalModel): Anime {
        return Anime(
            data.id,
            AnimeFormat.value(data.format),
            AnimeStatus.value(data.status),
            data.titles,
            data.descriptions,
            data.coverImage,
            data.score
        )
    }

    override fun mapFromDomainEntity(data: Anime): AnimeLocalModel {
        return AnimeLocalModel(
            data.id,
            data.format.ordinal,
            data.status.ordinal,
            data.titles,
            data.descriptions,
            data.coverImage,
            data.score
        )
    }
}