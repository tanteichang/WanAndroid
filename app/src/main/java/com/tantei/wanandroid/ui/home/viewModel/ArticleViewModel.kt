package com.tantei.wanandroid.ui.home.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.tantei.wanandroid.ui.home.bean.Article
import com.tantei.wanandroid.repositories.ArticleRepository

class ArticleViewModel : ViewModel() {

   private val pageLiveData = MutableLiveData<Int>()

   val articleList = ArrayList<Article>()
   val topArticleList = ArrayList<Article>()

   val articleListData = Transformations.switchMap(pageLiveData) { page ->
      ArticleRepository.get().getArticleList(page, page == 0)
   }

   fun getArticleList(page: Int) {
      pageLiveData.value = page
   }
}