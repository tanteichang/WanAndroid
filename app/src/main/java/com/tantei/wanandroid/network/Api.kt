package com.tantei.wanandroid.network


import com.tantei.wanandroid.ui.home.bean.Article
import com.tantei.wanandroid.ui.home.bean.ArticleListResponse
import com.tantei.wanandroid.ui.home.bean.BannerListResponse
import com.tantei.wanandroid.base.BaseResponse
import com.tantei.wanandroid.ui.home.bean.Banner
import com.tantei.wanandroid.ui.mine.bean.User
import retrofit2.Call
import retrofit2.http.*

sealed class ApiResult<out T> {
    data class Success<out T>(val result: T?): ApiResult<T>()
    data class Failure(val errorCode: Int, val errorMessage: String): ApiResult<Nothing>()
}

interface Api {

    @GET("/banner/json")
    suspend fun fetchBanner(): ApiResult<BaseResponse<List<Banner>>>

    @GET("/article/list/{page}/json")
    suspend fun fetchArticleList(@Path("page") page: Int = 0): ApiResult<BaseResponse<ArticleListResponse>>

    @GET("/article/top/json")
    suspend fun fetchTopArticles(): ApiResult<BaseResponse<List<Article>>>

    @GET("/lg/collect/list/{page}/json")
    suspend fun fetchCollectArticles(@Path("page") page: Int = 0): ApiResult<BaseResponse<ArticleListResponse>>

    // login
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@Field("username") username: String, @Field("password") password: String): ApiResult<BaseResponse<User>>
}