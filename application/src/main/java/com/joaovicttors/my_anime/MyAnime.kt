package com.joaovicttors.my_anime

import android.app.Application
import com.joaovicttors.my_anime.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyAnime : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        initInjections()
    }

    private fun initInjections() {
        startKoin { ->
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MyAnime)
            modules(AppModule.allModules())
        }
    }
}