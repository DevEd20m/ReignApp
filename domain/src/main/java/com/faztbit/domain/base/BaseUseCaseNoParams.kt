package com.faztbit.domain.base

import com.faztbit.domain.utils.Either
import com.faztbit.domain.utils.Failure
import kotlinx.coroutines.*


/**
 * By convention each [BaseUseCaseNoParams] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class BaseUseCaseNoParams<out Type> where Type : Any {

    abstract suspend fun run(): Either<Failure, Type>

    open operator fun invoke(
        scope: CoroutineScope,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            onResult(Either.Error(Failure.DataToDomainMapperFailure(throwable.message)))
        }
        val backgroundJob = scope.async(Dispatchers.IO) { run() }
        scope.launch(exceptionHandler) { onResult(backgroundJob.await()) }
    }
}