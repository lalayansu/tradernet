package com.example.domain.model

sealed interface ConnectionState {
    data object Connected : ConnectionState
    data object Disconnected : ConnectionState
    data class Success(val data: Quote) : ConnectionState
}
