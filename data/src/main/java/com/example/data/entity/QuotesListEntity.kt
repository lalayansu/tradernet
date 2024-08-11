package com.example.data.entity


data class QuotesListEntity(
    val buttons: ButtonsEntity? = null,
    val tickers: List<String?>? = null
)

data class ButtonsEntity(
    val blocks: List<BlockEntity?>? = null
)

data class ItemEntity(
    val action: String? = null,
    val color: Any? = null,
    val id: String? = null,
    val text: String? = null,
    val title: String? = null,
    val type: String? = null,
    val url: Any? = null
)

data class BlockEntity(
    val color: Any? = null,
    val group: Int? = null,
    val icon: String? = null,
    val items: List<ItemEntity?>? = null,
    val title: String? = null,
    val type: String? = null
)