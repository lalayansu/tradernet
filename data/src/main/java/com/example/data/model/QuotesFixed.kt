package com.example.data.model

import com.google.gson.Gson

enum class QuotesFixed(val id: String) {
    SP500(id = "SP500.IDX"),
    APPL_US(id = "AAPL.US"),
    RSTI(id = "RSTI"),
    GAZP(id = "GAZP"),
    MRKZ(id = "MRKZ"),
    RUAL(id = "RUAL"),
    HYDR(id = "HYDR"),
    MRKS(id = "MRKS"),
    SBER(id = "SBER"),
    FEES(id = "FEES"),
    TGKA(id = "TGKA"),
    VTBR(id = "VTBR"),
    ANH_US(id = "ANH.US"),
    VICL_US(id = "VICL.US"),
    BURG_US(id = "BURG.US"),
    NBL_US(id = "NBL.US"),
    YETI_US(id = "YETI.US"),
    WSFS_US(id = "WSFS.US"),
    NIO_US(id = "NIO.US"),
    DXC_US(id = "DXC.US"),
    MIC_US(id = "MIC.US"),
    HSBC_US(id = "HSBC.US"),
    EXPN_EU(id = "EXPN.EU"),
    GSK_EU(id = "GSK.EU"),
    SHP_EU(id = "SHP.EU"),
    MAN_EU(id = "MAN.EU"),
    DB1_EU(id = "DB1.EU"),
    MUV2_EU(id = "MUV2.EU"),
    TATE_EU(id = "TATE.EU"),
    KGF_EU(id = "KGF.EU"),
    MGGT_EU(id = "MGGT.EU"),
    SGGD_EU(id = "SGGD.EU"),
}

fun createSubscriptionRequest(quotes: List<String>? = null): String {
    val quotesList =
        quotes.takeIf { !it.isNullOrEmpty() } ?: emptyList() /*QuotesFixed.entries.map { it.id }*/
    val request = listOf("realtimeQuotes", quotesList)
    return Gson().toJson(request)
}