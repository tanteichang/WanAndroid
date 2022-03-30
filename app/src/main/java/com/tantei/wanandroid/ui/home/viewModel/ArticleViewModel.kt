package com.tantei.wanandroid.ui.home.viewModel

import androidx.lifecycle.*
import com.tantei.wanandroid.base.BaseViewModel
import com.tantei.wanandroid.ui.home.bean.Article
import com.tantei.wanandroid.repositories.ArticleRepository
import com.tantei.wanandroid.repositories.HomeRepository
import com.tantei.wanandroid.ui.home.bean.Banner

class ArticleViewModel : BaseViewModel() {

   private val pageLiveData = MutableLiveData<Int>()
   val bannerList: LiveData<List<Banner>> = liveData {
      val data = HomeRepository.getBannerList()
      emit(data.value!!)
   }

   val articleList = ArrayList<Article>()


   val articleListData = Transformations.switchMap(pageLiveData) { page ->
      ArticleRepository.get().getArticleList(page, page == 0)
   }

   fun getArticleList(page: Int) {
      pageLiveData.value = page
   }

}