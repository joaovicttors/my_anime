package com.joaovicttors.my_anime

import android.app.Application
import com.joaovicttors.anime.application.di.AnimeModule
import com.joaovicttors.my_anime.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initInjections()
    }

    private fun initInjections() {
        startKoin { ->
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MainApplication)

            modules(allModules())
        }
    }

    private fun allModules(): List<Module> = listOf(
        AppModule.providersModule,
        AppModule.servicesModule,
        AnimeModule.dataLayer,
        AnimeModule.domainLayer,
        AnimeModule.presentationLayer,
    )
}