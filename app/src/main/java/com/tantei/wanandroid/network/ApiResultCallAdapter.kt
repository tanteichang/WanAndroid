package com.tantei.wanandroid.network

import android.util.Log
import okhttp3.Request
import okio.Timeout
import retrofit2.*
import java.lang.UnsupportedOperationException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

private const val TAG = "ApiResultCallAdapter"

class ApiResultCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        check(getRawType(returnType) == Call::class.java) {"$returnType must be retrofit2.call"}
        check(returnType is ParameterizedType) {"$returnType must be parameterized. Raw types are not supported"}

        val apiResultType = getParameterUpperBound(0, returnType)
        check(getRawType(apiResultType) == ApiResult::class.java) {"$apiResultType must be ApiResult."}
        check(apiResultType is ParameterizedType) {"$apiResultType must be parameterized. Raw types are not supported"}

        val dataType = getParameterUpperBound(0, apiResultType)
        return ApiResultCallAdapter<Any>(dataType)
    }
}

class ApiResultCallAdapter<T>(private val type: Type) : CallAdapter<T, Call<ApiResult<T>>> {
    override fun responseType(): Type = type
    override fun adapt(call: Call<T>): Call<ApiResult<T>> {
        return ApiResultCall(call)
    }
}

class ApiResultCall<T>(private val delegate: Call<T>) : Call<ApiResult<T>> {
    override fun enqueue(callback: Callback<ApiResult<T>>) {
        delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                Log.d(TAG, "onResponse: $response")
                if (response.isSuccessful) {
                    val apiResult = if (response.body() == null) {
                        ApiResult.Failure(ApiError.dataIsNull.errorCode, ApiError.dataIsNull.errorMessage)
                    } else {
                        ApiResult.Success(response.body())
                    }
                    callback.onResponse(this@ApiResultCall, Response.success(apiResult))
                } else {
                  val failureApiResult = ApiResult.Failure(ApiError.httpStatusCodeError.errorCode, ApiError.httpStatusCodeError.errorMessage)
                    callback.onResponse(this@ApiResultCall, Response.success(failureApiResult))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
                val failureApiResult = if (t is ApiException) {
                    ApiResult.Failure(t.errorCode, t.errorMessage)
                } else {
                    ApiResult.Failure(ApiError.unknownException.errorCode, t.localizedMessage)
                }
                callback.onResponse(this@ApiResultCall, Response.success(failureApiResult))
            }
        })
    }

    override fun clone(): Call<ApiResult<T>> = ApiResultCall(delegate.clone())

    override fun execute(): Response<ApiResult<T>> {
        throw UnsupportedOperationException("ApiResultCall does not support sync execution")
    }

    override fun isExecuted(): Boolean {
        return delegate.isExecuted
    }

    override fun cancel() {
        delegate.cancel()
    }

    override fun isCanceled(): Boolean {
        return  delegate.isCanceled
    }

    override fun request(): Request {
        return delegate.request()
    }

    override fun timeout(): Timeout {
       return delegate.timeout()
    }
}