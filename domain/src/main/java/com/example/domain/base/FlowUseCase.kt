package com.example.domain.base

import com.example.domain.base.error.mapToAppError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in P, R>(private val dispatcher: CoroutineDispatcher) {

    operator fun invoke(parameters: P): Flow<Result<R>> {
        return execute(parameters)
            .catch { e ->
                emit(Result.Error(e.mapToAppError()))
            }
            .flowOn(dispatcher)
    }

    abstract fun execute(parameters: P): Flow<Result<R>>
}