package com.tantei.wanandroid.ui.home.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.tantei.wanandroid.base.BaseViewModel
import com.tantei.wanandroid.network.ApiResult
import com.tantei.wanandroid.ui.home.bean.Article
import com.tantei.wanandroid.repositories.ArticleRepository
import com.tantei.wanandroid.repositories.HomeRepository
import com.tantei.wanandroid.ui.home.bean.Banner
import com.tantei.wanandroid.utils.LLog
import kotlinx.coroutines.launch
import java.util.stream.LongStream

private const val TAG = "ArticleViewModel"

class ArticleViewModel : BaseViewModel() {
   private val _articleListLiveData: MutableLiveData<List<Article>> = MutableLiveData(emptyList())
   val articleList: LiveData<List<Article>>
      get() = _articleListLiveData
   val articles = ArrayList<Article>()

   fun getArticleList(page: Int) {
      LLog.d("getArticleList $page")
      viewModelScope.launch {
         if (page == 1) {
            when(val res = ArticleRepository.get().getArticleWithTop(page)) {
               is ApiResult.Success -> {
                  _articleListLiveData.value = res?.result.orEmpty()
               }
               is ApiResult.Failure -> {
                  Log.d(TAG, "getArticleList Failure: $res")
               }
            }
         } else {
            when(val res = ArticleRepository.get().getArticleList(page)) {
               is ApiResult.Success -> {
                  _articleListLiveData.value = res?.result?.data?.articleList
               }
               is ApiResult.Failure -> {
                  Log.d(TAG, "getArticleList Failure: $res")
               }
            }
         }
      }
   }


   private val _bannerListLiveData: MutableLiveData<List<Banner>> = MutableLiveData(emptyList())
   val bannerList: LiveData<List<Banner>>
      get() {
        return _bannerListLiveData
      }

   fun getBannerList() {
      viewModelScope.launch {
        when(val res = ArticleRepository.get().getBannerList()) {
           is ApiResult.Success -> {
              _bannerListLiveData.value = res.result?.data.orEmpty()
           }
           is ApiResult.Failure -> {
              Log.d(TAG, "getBannerList Failure: $res")
           }
        }
      }
   }
}