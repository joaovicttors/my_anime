package com.joaovicttors.my_anime

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.joaovicttors.data.repositories.AnimeRepositoryImpl
import com.joaovicttors.data.sources.AnimeRemoteDataSource
import com.joaovicttors.data.sources.AnimeRemoteDataSourceImpl
import com.joaovicttors.domain.entities.Response
import com.joaovicttors.domain.repositories.AnimeRepository
import com.joaovicttors.infrastructure.builders.RetrofitBuilder
import com.joaovicttors.infrastructure.services.AnimeRemoteService
import com.joaovicttors.infrastructure.services.retrofit.AnimeRetrofitService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animeService: AnimeRemoteService = RetrofitBuilder().build<AnimeRetrofitService>()
        val animeRemoteDataSource: AnimeRemoteDataSource = AnimeRemoteDataSourceImpl(animeService)
        val animeRepository: AnimeRepository = AnimeRepositoryImpl(animeRemoteDataSource)

        GlobalScope.launch {
            val response = animeRepository.retrieveRandomAnime()

            when (response) {
                is Response.Error -> Log.d("TEST123", response.message!!)
                is Response.Success -> Log.d("TEST123", response.body.toString())
            }

        }
    }
}