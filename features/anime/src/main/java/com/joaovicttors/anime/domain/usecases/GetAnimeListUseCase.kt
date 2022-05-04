package com.joaovicttors.anime.domain.usecases

import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.anime.domain.repositories.AnimeRepository
import com.joaovicttors.base.Response
import com.joaovicttors.base.usecase.BaseUseCase
import com.joaovicttors.base.usecase.utilities.NoParam

class GetAnimeListUseCase(
    private val animeRepository: AnimeRepository
) : BaseUseCase<NoParam, Response<List<Anime>>>() {

    override suspend operator fun invoke(param: NoParam): Response<List<Anime>> {

        return animeRepository.getAnimeListFromLocalStorage().let { response ->
            return@let when (response) {
                is Response.Error -> callRemoteStorage()
                is Response.Success -> if (response.data.isNotEmpty()) response else callRemoteStorage()
            }
        }
    }

    private suspend fun callRemoteStorage(): Response<List<Anime>> {
        return animeRepository.getAnimeListFromRemoteStorage().also { response ->
            if (response is Response.Success) animeRepository.insertAnimeListOnLocalStorage(response.data)
        }
    }
}