package com.example.domain.base

import com.example.domain.base.error.AppError

sealed class Result<out R> {
    data class Success<out T>(val data: T?) : Result<T>()
    data class Error(val error: AppError) : Result<Nothing>()
    object UncheckedError : Result<Nothing>()

    fun isSuccessful() = this is Success

    fun hasFailed() = this is Error

    override fun toString() = when (this) {
        is Success<*> -> "Success[data=$data]"
        is Error -> "Error[exception=$error]"
        is UncheckedError -> "Forbidden"
    }
}

inline fun <T> Result<T>.onSuccess(block: (T?) -> Unit): Result<T> {
    if (this is Result.Success<T>) {
        block(data)
    }

    return this
}

inline fun <T> Result<T>.onError(block: (AppError) -> Unit): Result<T> {
    if (this is Result.Error) {
        block(error)
    }

    return this
}

val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data