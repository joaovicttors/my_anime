package com.joaovicttors.domain.usecase

import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response
import com.joaovicttors.domain.repositories.AnimeRepository

class MarkAnimeAsFavoriteUseCase(
    private val animeRepository: AnimeRepository
) {

    suspend operator fun invoke(anime: Anime): Response<Unit> {
        return if (anime.favorite) delete(anime) else insert(anime)
    }

    private suspend fun delete(anime: Anime): Response<Unit> {
        return animeRepository.deleteAnime(anime).also { if (it is Response.Success) swapFavoriteVariable(anime) }
    }

    private suspend fun insert(anime: Anime): Response<Unit> {
        return animeRepository.insertAnime(anime).also { if (it is Response.Success) swapFavoriteVariable(anime) }
    }

    private fun swapFavoriteVariable(anime: Anime) {
        anime.favorite = !anime.favorite
    }
}