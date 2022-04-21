package com.joaovicttors.my_anime.di

import com.joaovicttors.data.repositories.AnimeRepositoryImpl
import com.joaovicttors.data.sources.AnimeRemoteDataSource
import com.joaovicttors.data.sources.AnimeRemoteDataSourceImpl
import com.joaovicttors.domain.repositories.AnimeRepository
import com.joaovicttors.infrastructure.builders.RetrofitBuilder
import com.joaovicttors.infrastructure.services.AnimeRemoteService
import com.joaovicttors.infrastructure.services.retrofit.AnimeRetrofitService
import com.joaovicttors.my_anime.features.detail.DetailViewModel
import com.joaovicttors.my_anime.features.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModule {

    fun allModules(): List<Module> = listOf(
        infrastructureModule,
        dataModule,
        applicationModule,
    )

    private val applicationModule: Module = module {
        viewModel { DetailViewModel(get()) }
        viewModel { HomeViewModel(get()) }
    }

    private val dataModule: Module = module {
        single<AnimeRemoteDataSource> { AnimeRemoteDataSourceImpl(get()) }

        single<AnimeRepository> { AnimeRepositoryImpl(get()) }
    }

    private val infrastructureModule: Module = module {
        single<AnimeRemoteService> { RetrofitBuilder().build<AnimeRetrofitService>() }
    }
}