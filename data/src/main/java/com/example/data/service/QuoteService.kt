package com.example.data.service

import com.example.data.model.response.QuotesListModel
import com.example.domain.model.request.GetQuotesRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface QuoteService {
    @POST("api/")
    suspend fun getQuotesList(
        @Body request: GetQuotesRequest
    ): QuotesListModel
}