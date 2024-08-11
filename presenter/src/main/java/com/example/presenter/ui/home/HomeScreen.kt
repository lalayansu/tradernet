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
        onAnimationComplete = { quote ->
            homeViewModel.updateQuote(quote.copy(shouldAnimate = false))
        },
        isLoading = state.isLoading,
    )
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    quoteList: List<Quote>,
    onAnimationComplete: (Quote) -> Unit = {}
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
            key = { _, q ->
                q.c.orEmpty()
            }
        ) { index, quote ->
            QuoteItemView(
                c = quote.c,
                ltr = quote.ltr,
                name = quote.name,
                pcp = quote.pcp,
                percentageChangeText = quote.percentageChangeText,
                shouldAnimate = quote.shouldAnimate,
                priceText = quote.priceText,
                changeText = quote.changeText,
                onAnimationComplete = {
                    onAnimationComplete(quote)
                }
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
                    c = "AAPL",
                    ltp = 145.0,
                    chg = 0.5,
                    pcp = 1.46,
                    shouldAnimate = true,
                    percentageChangeText = "+1.45%",
                    priceText = "145.00",
                    changeText = "0.5",
                    ltr = "NASDAQ",
                    name = "Apple Inc.",
                ),
                Quote(
                    c = "GAZP",
                    ltp = 145.0,
                    chg = 0.5,
                    pcp = -1.46,
                    priceText = "145.00",
                    changeText = "0.5",
                    shouldAnimate = true,
                    percentageChangeText = "-1.45%",
                    ltr = "MCX",
                    name = "Gazprom",
                ),
                Quote(
                    c = "YNDEX",
                    ltp = 145.0,
                    chg = 0.5,
                    pcp = 1.46,
                    shouldAnimate = false,
                    percentageChangeText = "+1.45%",
                    priceText = "145.00",
                    changeText = "0.5",
                    ltr = "MCX",
                    name = "Yandex",
                ),
                Quote(
                    c = "SBER",
                    ltp = 145.0,
                    chg = 0.5,
                    pcp = -1.46,
                    shouldAnimate = false,
                    percentageChangeText = "-1.45%",
                    priceText = "145.00",
                    changeText = "0.5",
                    ltr = "MCX",
                    name = "Sberbank",
                ),
            )
        )
    }
}