package com.joaovicttors.anime.data.mappers

import com.joaovicttors.anime.data.models.AnimeRemoteModel
import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.base.BaseMapper

class AnimeRemoteModelMapper : BaseMapper<AnimeRemoteModel, Anime>() {

    override fun mapToDomainEntity(data: AnimeRemoteModel): Anime {
        TODO("Not yet implemented")
    }

    override fun mapFromDomainEntity(data: Anime): AnimeRemoteModel {
        TODO("Not yet implemented")
    }
}