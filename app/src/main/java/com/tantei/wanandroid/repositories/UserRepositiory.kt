package com.tantei.wanandroid.repositories

import com.tantei.wanandroid.network.WanNetwork
import javax.inject.Inject

object UserRepository {
    suspend fun getUserInfo() = WanNetwork.fetchUserInfo()
}