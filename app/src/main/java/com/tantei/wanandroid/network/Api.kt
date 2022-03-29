package com.tantei.wanandroid.network


import com.tantei.wanandroid.ui.home.bean.Article
import com.tantei.wanandroid.ui.home.bean.ArticleListResponse
import com.tantei.wanandroid.ui.home.bean.BannerListResponse
import com.tantei.wanandroid.base.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/banner/json")
    fun fetchBanner(): Call<BannerListResponse>

    @GET("/article/list/{page}/json")
    fun fetchArticleList(@Path("page") page: Int = 0): Call<ArticleListResponse>
    @GET("/article/top/json")
    fun fetchTopArticles(): Call<BaseResponse<List<Article>>>
}