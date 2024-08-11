package com.example.data.di

import com.example.data.repository.QuoteRepositoryImpl
import com.example.data.repository.QuotesListRepositoryImpl
import com.example.data.service.QuoteScarletService
import com.example.data.service.QuoteService
import com.example.domain.repository.QuoteRepository
import com.example.domain.repository.QuotesListRepository
import com.tinder.scarlet.Scarlet
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(
    includes = [
        DataModule.QuotesDeclarations::class
    ]
)
@InstallIn(SingletonComponent::class)
class DataModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class QuotesDeclarations {
        @Binds
        abstract fun bindsQuotesListRepository(
            quotesListRepository: QuotesListRepositoryImpl
        ): QuotesListRepository

        @Binds
        abstract fun provideQuoteRepository(
            quoteRepository: QuoteRepositoryImpl,
        ): QuoteRepository
    }

    @Provides
    @Singleton
    fun provideHomeService(
        @RetrofitClientWithoutAuth retrofit: Retrofit,
    ): QuoteService = retrofit.create(QuoteService::class.java)

    @Provides
    @Singleton
    fun provideQuoteService(
        scarlet: Scarlet
    ): QuoteScarletService = scarlet.create<QuoteScarletService>()
}