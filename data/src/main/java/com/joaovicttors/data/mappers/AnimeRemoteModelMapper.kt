package com.joaovicttors.data.mappers

import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.infrastructure.models.RandomAnimeModel

object AnimeRemoteModelMapper {

    fun mapToEntity(response: RandomAnimeModel): List<Anime> {
        return response.data.map { model ->
            Anime(
                model.id,
                model.aniListId,
                model.malId,
                model.tmdbId,
                model.format,
                model.status,
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
                model.sequel,
                model.prequel,
                model.score,
                model.nsfw,
                model.recommendations
            )
        }
    }
}