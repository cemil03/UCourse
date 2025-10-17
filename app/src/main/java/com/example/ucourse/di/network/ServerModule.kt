package com.example.ucourse.di.network

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://drive.usercontent.google.com/"
internal val serversModule = module {
    single {
        provideRetrofit(
            baseUrl = BASE_URL,
        )
    }
}

private fun provideRetrofit(
    baseUrl: String
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
