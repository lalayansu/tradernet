package com.example.domain.model


data class Quote(
    val c: String? = null,
    val chg: Double? = null,
    val ltp: Double? = null,
    val ltr: String? = null,
    val name: String? = null,
    val pcp: Double? = null,
    val shouldAnimate: Boolean? = null,
    val percentageChangeText: String? = null,
    val changeText: String? = null,
    val priceText: String? = null
) {
    fun mergeWith(newData: Quote) = copy(
        c = newData.c.compareStrings(c),
        ltr = newData.ltr.compareStrings(ltr),
        name = newData.name.compareStrings(name),
        pcp = newData.pcp.compareDoubles(pcp),
        ltp = newData.ltp.compareDoubles(ltp),
        chg = newData.chg.compareDoubles(chg),
        shouldAnimate = newData.pcp != this.pcp,
        percentageChangeText = newData.pcp?.let { percent ->
            if (percent > 0) "+$percent%" else "$percent%"
        } ?: this.percentageChangeText,
        changeText = newData.changeText.compareStrings(changeText),
        priceText = newData.priceText.compareStrings(priceText)
    )

    private fun String?.compareStrings(oldString: String?) =
        takeIf { string -> !string.isNullOrBlank() && string != oldString } ?: this

    private fun Double?.compareDoubles(oldDouble: Double?) =
        takeIf { double -> double != null && double != 0.0 && double != oldDouble } ?: this
}