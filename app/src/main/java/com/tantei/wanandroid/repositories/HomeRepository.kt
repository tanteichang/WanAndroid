package com.tantei.wanandroid.repositories

import android.util.Log
import com.tantei.wanandroid.base.BaseRepository
import com.tantei.wanandroid.network.CODE
import com.tantei.wanandroid.network.WanNetwork
import kotlinx.coroutines.Dispatchers

private const val TAG = "HomeRepository"

object HomeRepository : BaseRepository() {
//    fun getBannerList() = fire(Dispatchers.IO) {
//        Log.d(TAG, "getBannerList: before")
//        val bannerListResponse = WanNetwork.fetchBannerList()
//        Log.d(TAG, "HomeRepository.getBannerList: $bannerListResponse")
//        if (bannerListResponse.errorCode == CODE.OK.code) {
//            val bannerList = bannerListResponse.data
//            Result.success(bannerList)
//        } else {
//            Result.failure((RuntimeException("response code is${bannerListResponse.errorCode}")))
//        }
//    }
}