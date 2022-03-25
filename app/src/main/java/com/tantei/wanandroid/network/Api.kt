package com.tantei.wanandroid.network


import com.tantei.wanandroid.models.ArticleListResponse
import com.tantei.wanandroid.models.BannerListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/banner/json")
    fun fetchBanner(): Call<BannerListResponse>

    @GET("/article/list/{page}/json")
    fun fetchArticleList(@Path("page") page: Int = 0): Call<ArticleListResponse>
}