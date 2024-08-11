package com.example.presenter.ui.home

import androidx.compose.runtime.Immutable
import com.example.domain.model.Quote

@Immutable
data class HomeContract(
    val data: List<Quote>,
    val isLoading: Boolean,
    val isOnline: Boolean,
    val error: String
) {
    companion object {
        fun initial(): HomeContract = HomeContract(
            data = emptyList(),
            isLoading = true,
            isOnline = false,
            error = ""
        )
    }
}