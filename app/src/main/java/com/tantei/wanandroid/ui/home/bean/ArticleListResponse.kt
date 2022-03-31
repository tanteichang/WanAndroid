package com.tantei.wanandroid.ui.home.bean

import com.google.gson.annotations.SerializedName

data class ArticleListResponse (
    val curPage: Int,
    @SerializedName("datas") val articleList: MutableList<Article>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)