package com.joaovicttors.infrastructure.services

import com.joaovicttors.infrastructure.models.RandomAnimeModel

interface AnimeRemoteService {
    suspend fun retrieveRandomAnimeAsync() : RandomAnimeModel
}