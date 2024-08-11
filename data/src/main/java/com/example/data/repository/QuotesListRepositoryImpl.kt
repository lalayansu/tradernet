package com.example.data.repository

import com.example.data.mapper.quotelist.QuoteListMapper
import com.example.data.mapper.quotelist.QuotesListModelToEntityMapper
import com.example.data.service.QuoteService
import com.example.domain.model.request.GetQuotesRequest
import com.example.domain.repository.QuotesListRepository
import javax.inject.Inject

class QuotesListRepositoryImpl @Inject constructor(
    private val quoteService: QuoteService,
    private val mapper: QuoteListMapper,
    private val quotesListModelToEntityMapper: QuotesListModelToEntityMapper
) : QuotesListRepository {
    override suspend fun getQuotesList(request: GetQuotesRequest) = mapper.toDomain(
        quotesListModelToEntityMapper.toEntity(quoteService.getQuotesList(request))
    )
}
