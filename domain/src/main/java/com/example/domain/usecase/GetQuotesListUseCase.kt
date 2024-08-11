package com.example.domain.usecase

import com.example.domain.base.UseCase
import com.example.domain.di.IoDispatcher
import com.example.domain.model.QuotesList
import com.example.domain.model.request.GetQuotesRequest
import com.example.domain.repository.QuotesListRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetQuotesListUseCase @Inject constructor(
    private val quotesListRepository: QuotesListRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : UseCase<GetQuotesRequest, QuotesList?>(ioDispatcher) {
    override suspend fun execute(parameters: GetQuotesRequest) =
        quotesListRepository.getQuotesList(parameters)
}