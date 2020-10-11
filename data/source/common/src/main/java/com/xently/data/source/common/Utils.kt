package com.xently.data.source.common

import com.xently.common.data.TaskResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

fun <T> Flow<TaskResult<T>>.withLoading(): Flow<TaskResult<T>> = flow {
    emit(TaskResult.Loading)
    emitAll(this@withLoading)
}