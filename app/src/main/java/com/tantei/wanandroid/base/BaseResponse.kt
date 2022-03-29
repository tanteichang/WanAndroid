package com.tantei.wanandroid.base

data class BaseResponse<T>(
    val data: T,
    val errorCode: Int,
    val errorMessage: String,
) {
}