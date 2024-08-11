package com.example.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitClientWithoutAuth

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpClientWithoutAuth
