package com.example.domain.repository

import com.example.domain.model.QuotesList
import com.example.domain.model.request.GetQuotesRequest

interface QuotesListRepository {
    suspend fun getQuotesList(request: GetQuotesRequest): QuotesList
}