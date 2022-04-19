package com.joaovicttors.infrastructure.builders

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitBuilder {

    inline fun <reified T> build(): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(buildClient())
            .addConverterFactory(buildConverter())
            .build()
            .create(T::class.java);
    }

    fun buildConverter(): MoshiConverterFactory {
        return MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        );
    }

    fun buildClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(buildLoggingInterceptor())
            .build();
    }

    private fun buildLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    companion object {
        const val BASE_URL: String = "https://api.aniapi.com/v1/"
    }
}