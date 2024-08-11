package com.example.domain.base

import com.example.domain.base.error.mapToAppError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(parameters: P): Result<R> = try {
        withContext(coroutineDispatcher) {
            execute(parameters).let {
                Result.Success(it)
            }
        }
    } catch (e: Throwable) {
        Result.Error(e.mapToAppError())
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}