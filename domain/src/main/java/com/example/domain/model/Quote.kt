package com.example.domain.model


data class Quote(
    val ticker: String? = null,
    val priceChangeInPoints: Double? = null,
    val latestTradePrice: Double? = null,
    val exchangeOfLatestTrade: String? = null,
    val name: String? = null,
    val priceChangeByPercentage: Double? = null,
    val shouldAnimatePercentageChange: Boolean? = null,
    val percentageChangeText: String? = null,
    val priceChangeInPointsText: String? = null,
    val latestTradePriceText: String? = null
) {
    fun mergeWith(newData: Quote) = copy(
        ticker = newData.ticker.compareStrings(ticker),
        exchangeOfLatestTrade = newData.exchangeOfLatestTrade.compareStrings(exchangeOfLatestTrade),
        name = newData.name.compareStrings(name),
        priceChangeByPercentage = newData.priceChangeByPercentage.compareDoubles(
            priceChangeByPercentage
        ),
        latestTradePrice = newData.latestTradePrice.compareDoubles(latestTradePrice),
        priceChangeInPoints = newData.priceChangeInPoints.compareDoubles(priceChangeInPoints),
        shouldAnimatePercentageChange = newData.priceChangeByPercentage?.isAnimationNeeded(),
        percentageChangeText = mergePercentageChangeText(newData.priceChangeByPercentage),
        priceChangeInPointsText = newData.priceChangeInPointsText.compareStrings(
            priceChangeInPointsText
        ),
        latestTradePriceText = newData.latestTradePriceText.compareStrings(latestTradePriceText)
    )

    private fun Double?.isAnimationNeeded(): Boolean =
        this != null && this != 0.0 && this != this@Quote.priceChangeByPercentage

    private fun mergePercentageChangeText(newPriceChangeByPercentage: Double?) =
        if (newPriceChangeByPercentage == null || newPriceChangeByPercentage == 0.0) {
            this.percentageChangeText
        } else {
            newPriceChangeByPercentage.let { percent ->
                if (percent > 0) "+$percent%" else "$percent%"
            }
        }

    private fun String?.compareStrings(oldString: String?) =
        takeIf { string -> !string.isNullOrBlank() && string != oldString } ?: oldString

    private fun Double?.compareDoubles(oldDouble: Double?) =
        takeIf { double -> double != null && double != 0.0 && double != oldDouble } ?: oldDouble
}