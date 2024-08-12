package com.example.presenter.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.data
import com.example.domain.base.onError
import com.example.domain.base.onSuccess
import com.example.domain.di.IoDispatcher
import com.example.domain.model.ConnectionState
import com.example.domain.model.Quote
import com.example.domain.model.request.GetQuotesRequest
import com.example.domain.usecase.GetQuotesListUseCase
import com.example.domain.usecase.GetRealtimeQuotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.util.concurrent.LinkedBlockingQueue
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRealtimeQuotesUseCase: GetRealtimeQuotesUseCase,
    private val getQuotesListUseCase: GetQuotesListUseCase,
    @IoDispatcher private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeContract.initial())
    val uiState = _uiState.asStateFlow()

    private val _tickers = MutableStateFlow<List<String>>(emptyList())

    private val updateMutex = Mutex()
    private val quoteQueue = LinkedBlockingQueue<Quote>()

    init {
        observeTickers()
        getQuotesList()
    }

    private fun observeTickers() {
        viewModelScope.launch(dispatcherIO) {
            _tickers.collect { tickers ->
                if (tickers.isNotEmpty()) {
                    getRealtimeQuotes(list = tickers)
                }
            }
        }
    }

    private fun getQuotesList() {
        viewModelScope.launch(dispatcherIO) {
            getQuotesListUseCase(
                parameters = GetQuotesRequest(
                    q = GetQuotesRequest.Q(
                        cmd = "getTopSecurities",
                        params = GetQuotesRequest.Q.Params(
                            exchange = "russia",
                            gainers = 0,
                            limit = 30,
                            type = "stocks"
                        )
                    )
                )
            ).onError {
                Log.e("WSTRDNT", "list error -> ${it.commonErrorCode}")
            }.onSuccess { quotesList ->
                _tickers.value = quotesList?.tickers.orEmpty().map { it.orEmpty() }
            }
        }
    }

    private fun getRealtimeQuotes(list: List<String>?) {
        viewModelScope.launch(dispatcherIO) {
            getRealtimeQuotesUseCase(
                parameters = list.orEmpty()
            ).onStart {
                _uiState.update { contract -> contract.copy(isLoading = true) }
            }.onEach { connectionStateResult ->
                val event = connectionStateResult.data ?: ConnectionState.Disconnected
                when (event) {
                    is ConnectionState.Connected -> {
                        Log.e("WSTRDNT", "observeEvent: OnConnectionOpened")
                    }

                    ConnectionState.Disconnected -> {
                        Log.e("WSTRDNT", "observeEvent: Disconnected")
                    }

                    is ConnectionState.Success -> {
                        Log.e("WSTRDNT", "observeEvent: Success")

                        quoteQueue.offer(event.data.copy(shouldAnimatePercentageChange = true))
                        processQuoteQueue()
                    }
                }
            }.catch { e ->
                _uiState.update { contract ->
                    contract.copy(
                        isLoading = false,
                        error = e.message ?: "Unknown error"
                    )
                }
            }.collect()
        }
    }

    private fun processQuoteQueue() {
        viewModelScope.launch(dispatcherIO) {
            while (quoteQueue.isNotEmpty()) {
                val quote = quoteQueue.poll()
                quote?.let { processQuote(it) }
            }
        }
    }

    private suspend fun processQuote(quote: Quote) = updateMutex.withLock {
        withContext(Dispatchers.Main) {
            _uiState.update { contract ->
                val updatedList = contract.data.toMutableList()
                val index = updatedList.indexOfFirst { it.ticker == quote.ticker }
                if (index != -1) {
                    val updatedStock = updatedList[index].mergeWith(quote)
                    updatedList[index] = updatedStock
                } else {
                    updatedList.add(quote)
                }

                contract.copy(data = updatedList, isLoading = false)
            }
        }
    }
}