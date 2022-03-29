package com.tantei.wanandroid.ui.home.bean

import com.google.gson.annotations.SerializedName


enum class ArticleType(val type: Int) {
    @SerializedName("0")
    NORMAL(0),
    @SerializedName("1")
    TOP(1),
}

data class Article(
    val id: Int,
    val link: String, // 文章链接
    val publishTime: Long, // 发布时间
    val title: String, // 标题
    val shareUser: String, // 分享人,
    val type: ArticleType,
)