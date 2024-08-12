package com.example.data.repository

import com.example.data.entity.QuoteEntity
import com.example.data.mapper.quote.QuoteMapper
import com.example.data.mapper.quote.QuoteModelToEntityMapper
import com.example.data.model.createSubscriptionRequest
import com.example.data.model.response.QuoteModel
import com.example.data.service.QuoteScarletService
import com.example.domain.di.IoDispatcher
import com.example.domain.model.ConnectionState
import com.example.domain.repository.QuoteRepository
import com.google.gson.Gson
import com.tinder.scarlet.Message
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteRepositoryImpl @Inject constructor(
    private val quoteMapper: QuoteMapper,
    private val quoteModelToEntityMapper: QuoteModelToEntityMapper,
    @IoDispatcher private val dispatcherIO: CoroutineDispatcher,
    private val quoteScarletService: QuoteScarletService
) : QuoteRepository {

    private val _tickerUpdates = MutableSharedFlow<QuoteEntity>()
    private val tickerUpdates = _tickerUpdates.asSharedFlow()

    init {
        CoroutineScope(dispatcherIO).launch {
            quoteScarletService.observeEvent().collect { event ->
                val gson = Gson()
                when (event) {
                    is WebSocket.Event.OnMessageReceived -> {
                        val message = (event.message as Message.Text).value
                        val jsonArray = gson.fromJson(message, Array::class.java)

                        if (jsonArray != null && jsonArray.size == 2 && jsonArray[0] == "q") {
                            val stockDataJson = gson.toJson(jsonArray[1])
                            val stockData = gson.fromJson(stockDataJson, QuoteModel::class.java)

                            if (stockData != null) {
                                val stockEntity = quoteModelToEntityMapper.toEntity(stockData)
                                _tickerUpdates.emit(stockEntity)
                            }
                        }
                    }

                    else -> Unit
                }
            }
        }
    }

    override fun observeEvent(quotes: List<String>?): Flow<ConnectionState> =
        quoteScarletService.observeEvent().map { event ->
            when (event) {
                is WebSocket.Event.OnConnectionOpened<*> -> {
                    subscribeQuote(quotes = quotes)
                    ConnectionState.Connected
                }

                is WebSocket.Event.OnMessageReceived -> ConnectionState.Connected

                else -> ConnectionState.Disconnected
            }
        }

    override fun observeQuote(): Flow<ConnectionState> = flow {
        emitAll(
            tickerUpdates.map {
                ConnectionState.Success(data = quoteMapper.toDomain(it))
            }
        )
    }

    override fun subscribeQuote(quotes: List<String>?) =
        quoteScarletService.sendSubscribe(createSubscriptionRequest(quotes = quotes))
}