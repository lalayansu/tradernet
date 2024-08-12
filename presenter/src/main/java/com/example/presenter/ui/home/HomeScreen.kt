package com.example.presenter.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.Quote
import com.example.presenter.components.brush.SkeletonListLoader
import com.example.presenter.components.item.QuoteItemView
import com.example.presenter.theme.TradernetTheme

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val state by homeViewModel.uiState.collectAsState()

    HomeScreenContent(
        modifier = Modifier,
        quoteList = state.data,
        isLoading = state.isLoading,
    )
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    quoteList: List<Quote>,
) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            SkeletonListLoader(times = 12)
        }

        return
    }

    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
        userScrollEnabled = true,
        state = lazyListState,
    ) {
        itemsIndexed(
            items = quoteList,
            key = { _, quote ->
                quote.ticker.orEmpty()
            }
        ) { index, quote ->
            QuoteItemView(
                ticker = quote.ticker,
                exchangeOfLatestTrade = quote.exchangeOfLatestTrade,
                name = quote.name,
                priceChangeByPercentage = quote.priceChangeByPercentage,
                percentageChangeText = quote.percentageChangeText,
                shouldAnimatePercentageChange = quote.shouldAnimatePercentageChange,
                latestTradePriceText = quote.latestTradePriceText,
                priceChangeInPointsText = quote.priceChangeInPointsText,
            )

            if (index < quoteList.lastIndex)
                Divider(
                    modifier = Modifier.padding(start = 4.dp),
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
                    thickness = 1.dp
                )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    TradernetTheme {
        HomeScreenContent(
            quoteList = listOf(
                Quote(
                    ticker = "AAPL",
                    latestTradePrice = 145.0,
                    priceChangeInPoints = 0.5,
                    priceChangeByPercentage = 1.46,
                    shouldAnimatePercentageChange = true,
                    percentageChangeText = "+1.45%",
                    latestTradePriceText = "145.00",
                    priceChangeInPointsText = "0.5",
                    exchangeOfLatestTrade = "NASDAQ",
                    name = "Apple Inc.",
                ),
                Quote(
                    ticker = "GAZP",
                    latestTradePrice = 145.0,
                    priceChangeInPoints = 0.5,
                    priceChangeByPercentage = -1.46,
                    latestTradePriceText = "145.00",
                    priceChangeInPointsText = "0.5",
                    shouldAnimatePercentageChange = true,
                    percentageChangeText = "-1.45%",
                    exchangeOfLatestTrade = "MCX",
                    name = "Gazprom",
                ),
                Quote(
                    ticker = "YNDEX",
                    latestTradePrice = 145.0,
                    priceChangeInPoints = 0.5,
                    priceChangeByPercentage = 1.46,
                    shouldAnimatePercentageChange = false,
                    percentageChangeText = "+1.45%",
                    latestTradePriceText = "145.00",
                    priceChangeInPointsText = "0.5",
                    exchangeOfLatestTrade = "MCX",
                    name = "Yandex",
                ),
                Quote(
                    ticker = "SBER",
                    latestTradePrice = 145.0,
                    priceChangeInPoints = 0.5,
                    priceChangeByPercentage = -1.46,
                    shouldAnimatePercentageChange = false,
                    percentageChangeText = "-1.45%",
                    latestTradePriceText = "145.00",
                    priceChangeInPointsText = "0.5",
                    exchangeOfLatestTrade = "MCX",
                    name = "Sberbank",
                ),
            )
        )
    }
}