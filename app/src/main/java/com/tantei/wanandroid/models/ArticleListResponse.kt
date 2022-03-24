package com.tantei.wanandroid.models

import com.google.gson.annotations.SerializedName

data class ArticleListResponse (
    val data: ArticleList,
    val errorCode: Int,
    val errorMsg: String
) {
    data class ArticleList (
        val curPage: Int,
        @SerializedName("datas") val articleList: MutableList<Article>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
    )
}

