package com.tantei.wanandroid.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.tantei.wanandroid.base.BaseViewModel
import com.tantei.wanandroid.models.Banner
import com.tantei.wanandroid.repositories.HomeRepository

class HomeViewModel : BaseViewModel() {
    val bannerList = HomeRepository.getBannerList()
}
