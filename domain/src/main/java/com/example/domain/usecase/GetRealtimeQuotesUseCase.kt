package com.example.domain.usecase

import com.example.domain.base.FlowUseCase
import com.example.domain.base.Result
import com.example.domain.di.IoDispatcher
import com.example.domain.model.ConnectionState
import com.example.domain.repository.QuoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class GetRealtimeQuotesUseCase @Inject constructor(
    private val repository: QuoteRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<List<String>?, ConnectionState>(dispatcher) {

    override fun execute(parameters: List<String>?): Flow<Result<ConnectionState>> = merge(
        repository.observeEvent(parameters.orEmpty()).map {
            Result.Success(it)
        },
        repository.observeQuote().map {
            Result.Success(it)
        }
    )
}