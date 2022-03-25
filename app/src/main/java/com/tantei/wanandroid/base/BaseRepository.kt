package com.tantei.wanandroid.base

import androidx.lifecycle.liveData
import kotlin.coroutines.CoroutineContext

abstract class BaseRepository {
    protected fun <T>fire(context: CoroutineContext, block: suspend () -> Result<T>) = liveData<Result<T>>(context) {
        val result = try {
            block()
        } catch (e: Exception) {
            Result.failure<T>(e)
        }
        emit(result)
    }
}