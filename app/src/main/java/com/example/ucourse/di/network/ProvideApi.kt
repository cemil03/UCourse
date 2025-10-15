package com.example.ucourse.di.network

import retrofit2.Retrofit

inline fun <reified Api> provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
