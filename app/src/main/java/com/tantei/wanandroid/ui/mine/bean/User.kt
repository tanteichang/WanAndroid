package com.tantei.wanandroid.ui.mine.bean

data class User (
    val admin: Boolean,
    val coinCount: Int,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val publicName: String,
    val type: String,
    val username: String,
)