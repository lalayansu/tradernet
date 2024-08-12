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
        shouldAnimate = newData.pcp != null && newData.pcp != 0.0 && newData.pcp != this.pcp,
        percentageChangeText = percentageText(newData.pcp),
        changeText = newData.changeText.compareStrings(changeText),
        priceText = newData.priceText.compareStrings(priceText)
    )

    private fun percentageText(newPcp: Double?): String? {
        return if (newPcp == null || newPcp == 0.0) {
            this.percentageChangeText
        } else {
            newPcp.let { percent ->
                if (percent > 0) "+$percent%" else "$percent%"
            }
        }
    }

    private fun String?.compareStrings(oldString: String?) =
        takeIf { string -> !string.isNullOrBlank() && string != oldString } ?: oldString

    private fun Double?.compareDoubles(oldDouble: Double?) =
        takeIf { double -> double != null && double != 0.0 && double != oldDouble } ?: oldDouble
}