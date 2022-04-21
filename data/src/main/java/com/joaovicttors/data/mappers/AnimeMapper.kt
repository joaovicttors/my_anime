package com.joaovicttors.data.mappers

import com.joaovicttors.domain.aggregate.AnimeFormat
import com.joaovicttors.domain.aggregate.AnimeStatus
import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.infrastructure.models.AnimeModel

object AnimeMapper {
    fun modelToEntity(model: AnimeModel) = Anime(
        model.id,
        model.aniListId,
        model.malId,
        model.tmdbId,
        AnimeFormat.getByValue(model.format),
        AnimeStatus.getByValue(model.status),
        model.titles,
        model.descriptions,
        model.startDate,
        model.endDate,
        model.weeklyAiringDay,
        model.seasonPeriod,
        model.seasonYear,
        model.episodesCount,
        model.episodeDuration,
        model.trailerUrl,
        model.coverImage,
        model.hasCoverImage,
        model.coverColor,
        model.bannerImage,
        model.genres,
        model.sagas?.map { SagaMapper.modelToEntity(it) },
        model.sequel,
        model.prequel,
        model.score,
        model.nsfw,
        model.recommendations
    )
}