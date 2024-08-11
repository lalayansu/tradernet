package com.example.domain.model


data class QuotesList(
    val buttons: Buttons? = null,
    val tickers: List<String?>? = null
)

data class Buttons(
    val blocks: List<Block?>? = null
)

data class Item(
    val action: String? = null,
    val color: Any? = null,
    val id: String? = null,
    val text: String? = null,
    val title: String? = null,
    val type: String? = null,
    val url: Any? = null
)

data class Block(
    val color: Any? = null,
    val group: Int? = null,
    val icon: String? = null,
    val items: List<Item?>? = null,
    val title: String? = null,
    val type: String? = null
)