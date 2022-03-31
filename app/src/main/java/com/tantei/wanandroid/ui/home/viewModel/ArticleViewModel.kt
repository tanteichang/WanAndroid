package com.tantei.wanandroid.ui.home.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.tantei.wanandroid.base.BaseViewModel
import com.tantei.wanandroid.network.ApiResult
import com.tantei.wanandroid.network.WanNetwork
import com.tantei.wanandroid.ui.home.bean.Article
import com.tantei.wanandroid.repositories.ArticleRepository
import com.tantei.wanandroid.repositories.HomeRepository
import com.tantei.wanandroid.ui.home.bean.Banner
import kotlinx.coroutines.launch

private const val TAG = "ArticleViewModel"

class ArticleViewModel : BaseViewModel() {
   val articleListLiveData: MutableLiveData<List<Article>> = MutableLiveData(emptyList())
   val articleList = ArrayList<Article>()
   fun getArticleList(page: Int) {
      viewModelScope.launch {
         when(val result = ArticleRepository.get().getArticleList(page)) {
            is ApiResult.Success -> {
               articleListLiveData.value = result?.data?.data?.articleList!!
            }
            is ApiResult.Failure -> {
               Log.d(TAG, "getArticleList Failure: $result")
            }
         }
      }
   }


   val bannerListLiveData: MutableLiveData<List<Banner>> = MutableLiveData()
   val bannerList = ArrayList<Banner>()
   fun getBannerList() {
      viewModelScope.launch {
        when(val result = ArticleRepository.get().getBannerList()) {
           is ApiResult.Success -> {
              bannerListLiveData.value = result.data?.data.orEmpty()
           }
           is ApiResult.Failure -> {
              Log.d(TAG, "getBannerList Failure: $result")
           }
        }
      }
   }
}