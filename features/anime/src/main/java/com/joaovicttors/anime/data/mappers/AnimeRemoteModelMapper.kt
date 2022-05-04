package com.joaovicttors.anime.data.mappers

import com.joaovicttors.anime.data.models.AnimeRemoteModel
import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.base.BaseMapper

class AnimeRemoteModelMapper : BaseMapper<AnimeRemoteModel.Documents, Anime>() {

    override fun mapToDomainEntity(data: AnimeRemoteModel.Documents): Anime {
        TODO("Not yet implemented")
    }

    override fun mapFromDomainEntity(data: Anime): AnimeRemoteModel.Documents {
        TODO("Not yet implemented")
    }
}