package com.example.domain.repository

import com.example.domain.model.ConnectionState
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    fun observeEvent(quotes: List<String>?): Flow<ConnectionState>
    fun observeQuote(): Flow<ConnectionState>
    fun subscribeQuote(quotes: List<String>?)
}