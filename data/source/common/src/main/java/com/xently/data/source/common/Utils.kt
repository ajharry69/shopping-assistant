package com.xently.data.source.common

import com.xently.common.data.TaskResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun <T> Flow<TaskResult<T>>.whileLoading(
    flowOn: CoroutineContext = EmptyCoroutineContext,
): Flow<TaskResult<T>> = flow {
    emit(TaskResult.Loading)
    emitAll(this@whileLoading)
}.flowOn(flowOn)