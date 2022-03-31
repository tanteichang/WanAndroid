package com.tantei.wanandroid.network

object ApiError {
    val dataIsNull = Error(-1, "data is null")
    val httpStatusCodeError = Error(-2, "Server error. Please try again later.")
    val unknownException = Error(-3,"unknown exception")
}

data class Error(val errorCode: Int, val errorMessage: String)