package com.tantei.wanandroid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.tantei.wanandroid.models.Article
import com.tantei.wanandroid.repositories.ArticleRepository

class ArticleViewModel : ViewModel() {

   private val pageLiveData = MutableLiveData<Int>()

   val articleList = ArrayList<Article>()

   val articleListData = Transformations.switchMap(pageLiveData) { page ->
      ArticleRepository.getArticleList(page)
   }

   fun getArticleList(page: Int) {
      pageLiveData.value = page
   }
}