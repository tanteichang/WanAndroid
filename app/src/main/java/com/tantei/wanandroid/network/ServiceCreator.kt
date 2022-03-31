package com.tantei.wanandroid.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// https://blog.csdn.net/taotao110120119/article/details/110878525
object ServiceCreator {
    private const val BASE_URL = "https://www.wanandroid.com"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(BusinessErrorInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(ApiResultCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}