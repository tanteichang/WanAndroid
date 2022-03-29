package com.tantei.wanandroid.network

import com.tantei.wanandroid.base.BaseNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

enum class CODE(val code: Int) {
    OK(0)
}

object WanNetwork : BaseNetwork() {

    private val wanApiService = ServiceCreator.create(Api::class.java)


    suspend fun fetchArticleList(page: Int) = wanApiService.fetchArticleList(page).await()
    suspend fun fetchTopArticleList() = wanApiService.fetchTopArticles().await()
    suspend fun fetchBannerList() = wanApiService.fetchBanner().await()
}