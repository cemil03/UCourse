package com.example.ucourse.di.network


import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

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
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(createGsonFactory())
        .build()
}

private fun createGsonFactory(): GsonConverterFactory {
    val gson = GsonBuilder()
        .setLenient()
        .create()
    return GsonConverterFactory.create(gson)
}

