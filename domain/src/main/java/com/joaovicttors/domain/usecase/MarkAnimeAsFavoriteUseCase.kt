package com.joaovicttors.domain.usecase

import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response
import com.joaovicttors.domain.repositories.AnimeRepository

class MarkAnimeAsFavoriteUseCase(
    private val animeRepository: AnimeRepository
) {

    suspend operator fun invoke(anime: Anime): Response<Unit> {
        anime.favorite = !anime.favorite
        return if (anime.favorite) animeRepository.insertAnime(anime) else animeRepository.deleteAnime(anime)
    }
}