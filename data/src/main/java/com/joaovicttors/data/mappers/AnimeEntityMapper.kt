package com.joaovicttors.data.mappers

import com.joaovicttors.data.sources.local.models.AnimeEntity
import com.joaovicttors.domain.aggregate.AnimeFormat
import com.joaovicttors.domain.aggregate.AnimeStatus
import com.joaovicttors.domain.entities.Anime

class AnimeEntityMapper {

    fun mapToDomainEntity(entity: AnimeEntity) : Anime {
        return Anime(
            entity.id,
            AnimeFormat.getByValue(entity.format),
            AnimeStatus.getByValue(entity.status),
            entity.titles,
            entity.descriptions,
            entity.coverImage,
            entity.score
        )
    }

    fun mapFromDomainEntity(domainEntity: Anime) : AnimeEntity {
        return AnimeEntity(
            domainEntity.id,
            domainEntity.format.ordinal,
            domainEntity.status.ordinal,
            domainEntity.titles,
            domainEntity.descriptions,
            domainEntity.coverImage,
            domainEntity.score
        )
    }
}