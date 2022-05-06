package com.joaovicttors.my_anime.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.joaovicttors.anime.application.di.AnimeModule
import com.joaovicttors.anime.data.datasources.remote.service.RetrofitAnimeService
import com.joaovicttors.my_anime.DatabaseTest
import com.joaovicttors.my_anime.constants.RetrofitConstants
import com.joaovicttors.my_anime.constants.RoomConstants
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object AppModule {

    val providersModule = module { ->

        single { Dispatchers.IO }

        single {
            Retrofit.Builder()
                .baseUrl(RetrofitConstants.BASE_URL)
                .client(OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }

        single {
            Room.databaseBuilder(
                androidApplication(),
                DatabaseTest::class.java,
                RoomConstants.DATABASE_NAME
            ).build()
        }
    }

    val servicesModule = module { ->

        // ANIME SERVICES
        single { get<Retrofit>().create<RetrofitAnimeService>() }
        single { get<DatabaseTest>().roomAnimeService() }
    }
}