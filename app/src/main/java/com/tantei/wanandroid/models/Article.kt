package com.tantei.wanandroid.models

data class Article(
    val id: Int,
    val link: String, // 文章链接
    val publishTime: Long, // 发布时间
    val title: String, // 标题
    val shareUser: String // 分享人
)