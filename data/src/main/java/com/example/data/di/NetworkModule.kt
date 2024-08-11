package com.example.data.di

import com.example.tradernet_test_task.data.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @Named("gson")
    fun provideGson(): Gson = GsonBuilder().create()

    @OkHttpClientWithoutAuth
    @Provides
    @Singleton
    fun provideOkHttpClientWithoutAuth(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
            .setLevel(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            )

        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    @RetrofitClientWithoutAuth
    @Singleton
    @Provides
    fun providesRetrofitWithoutAuth(
        @OkHttpClientWithoutAuth client: OkHttpClient,
        @Named("gson") gson: Gson,
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .build()
}