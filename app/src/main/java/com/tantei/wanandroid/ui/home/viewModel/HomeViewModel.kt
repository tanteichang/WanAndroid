package com.tantei.wanandroid.ui.home.viewModel

import com.tantei.wanandroid.base.BaseViewModel
import com.tantei.wanandroid.repositories.HomeRepository

class HomeViewModel : BaseViewModel() {
    val bannerList = HomeRepository.getBannerList()
}
