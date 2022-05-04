package com.joaovicttors.anime.data.mappers

import com.joaovicttors.anime.data.models.AnimeLocalModel
import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.base.BaseMapper

class AnimeLocalModelMapper : BaseMapper<AnimeLocalModel, Anime>() {

    override fun mapToDomainEntity(data: AnimeLocalModel): Anime {
        TODO("Not yet implemented")
    }

    override fun mapFromDomainEntity(data: Anime): AnimeLocalModel {
        TODO("Not yet implemented")
    }
}