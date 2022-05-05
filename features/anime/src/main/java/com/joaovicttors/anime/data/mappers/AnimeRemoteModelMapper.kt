package com.joaovicttors.anime.data.mappers

import com.joaovicttors.anime.data.models.AnimeRemoteModel
import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.anime.domain.enums.AnimeFormat
import com.joaovicttors.anime.domain.enums.AnimeStatus
import com.joaovicttors.base.BaseMapper

class AnimeRemoteModelMapper : BaseMapper<AnimeRemoteModel.Documents, Anime>() {

    override fun mapToDomainEntity(data: AnimeRemoteModel.Documents): Anime {
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

    override fun mapFromDomainEntity(data: Anime): AnimeRemoteModel.Documents {
        TODO("Not yet implemented")
    }
}