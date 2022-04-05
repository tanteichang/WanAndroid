package com.tantei.wanandroid.network

import androidx.lifecycle.LiveData
import com.tantei.wanandroid.base.BaseNetwork
import com.tantei.wanandroid.base.BaseResponse
import com.tantei.wanandroid.ui.home.bean.Article
import com.tantei.wanandroid.ui.home.bean.ArticleListResponse
import com.tantei.wanandroid.ui.home.bean.Banner
import kotlinx.coroutines.delay
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

    /**
     * page: Int -> 为了对应响应的curPage， 从1开始
     */
    suspend fun fetchArticleList(page: Int) = wanApiService.fetchArticleList(page - 1)
    suspend fun fetchTopArticleList() = wanApiService.fetchTopArticles()
    suspend fun fetchBannerList() = wanApiService.fetchBanner()
}