package com.joaovicttors.my_anime.di

import androidx.room.Room
import com.joaovicttors.data.mappers.AnimeEntityMapper
import com.joaovicttors.data.mappers.AnimeResponseMapper
import com.joaovicttors.data.repositories.AnimeRepositoryImpl
import com.joaovicttors.data.sources.local.AnimeLocalDataSource
import com.joaovicttors.data.sources.local.AnimeLocalDataSourceImpl
import com.joaovicttors.data.sources.local.service.AnimeLocalService
import com.joaovicttors.data.sources.remote.AnimeRemoteDataSource
import com.joaovicttors.data.sources.remote.AnimeRemoteDataSourceImpl
import com.joaovicttors.data.sources.remote.service.AnimeRemoteService
import com.joaovicttors.domain.repositories.AnimeRepository
import com.joaovicttors.domain.usecase.MarkAnimeAsFavoriteUseCase
import com.joaovicttors.domain.usecase.RetrieveAnimeListUseCase
import com.joaovicttors.domain.usecase.RetrieveSpecificAnimeUseCase
import com.joaovicttors.infrastructure.builders.RetrofitBuilder
import com.joaovicttors.infrastructure.builders.RoomBuilder
import com.joaovicttors.infrastructure.services.AnimeRetrofitService
import com.joaovicttors.my_anime.features.detail.DetailViewModel
import com.joaovicttors.my_anime.features.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModule {

    fun allModules(): List<Module> = listOf(
        infrastructureModule,
        dataModule,
        domainModule,
        applicationModule,
    )

    private val applicationModule: Module = module {
        viewModel { DetailViewModel(get()) }
        viewModel { HomeViewModel(get(), get()) }
    }

    private val domainModule: Module = module {
        single { MarkAnimeAsFavoriteUseCase(get()) }
        single { RetrieveAnimeListUseCase(get()) }
        single { RetrieveSpecificAnimeUseCase(get()) }
    }

    private val dataModule: Module = module {
        single { AnimeEntityMapper() }
        single { AnimeResponseMapper() }

        single<AnimeRemoteDataSource> { AnimeRemoteDataSourceImpl(get(), get()) }
        single<AnimeLocalDataSource> { AnimeLocalDataSourceImpl(get(), get()) }

        single<AnimeRepository> { AnimeRepositoryImpl(get(), get()) }
    }

    private val infrastructureModule: Module = module {
        single { RetrofitBuilder() }
        single { Room.databaseBuilder(androidContext(), RoomBuilder::class.java, "database-name").build() }

        single<AnimeRemoteService> { get<RetrofitBuilder>().build<AnimeRetrofitService>() }
        single<AnimeLocalService>  { get<RoomBuilder>().animeDao() }
    }
}