package com.tantei.wanandroid.repositories

import com.tantei.wanandroid.base.BaseRepository
import com.tantei.wanandroid.network.CODE
import com.tantei.wanandroid.network.WanNetwork
import kotlinx.coroutines.Dispatchers

object HomeRepository : BaseRepository() {
    fun getBannerList() = fire(Dispatchers.IO) {
        val bannerListResponse = WanNetwork.fetchBannerList()
        if (bannerListResponse.errorCode == CODE.OK.code) {
            val bannerList = bannerListResponse.data
            Result.success(bannerList)
        } else {
            Result.failure((RuntimeException("response code is${bannerListResponse.errorCode}")))
        }
    }
}