package com.tantei.wanandroid.network

import com.tantei.wanandroid.base.BaseNetwork

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
    suspend fun fetchCollectArticles(page: Int) = wanApiService.fetchCollectArticles(page)

    // login
    suspend fun doLogin(username: String, password: String) = wanApiService.login(username, password)
    // userinfo
    suspend fun fetchUserInfo() = wanApiService.fetchUserInfo()
}