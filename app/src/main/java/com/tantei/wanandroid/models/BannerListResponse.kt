package com.tantei.wanandroid.models

data class BannerListResponse (
    val data: List<Banner>,
    val errorCode: Int,
    val errorMsg: String
)
