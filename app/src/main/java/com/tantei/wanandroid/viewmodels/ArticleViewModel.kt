package com.tantei.wanandroid.viewmodels

import androidx.lifecycle.ViewModel
import com.tantei.wanandroid.models.Article

class ArticleViewModel : ViewModel() {
   val articleList = ArrayList<Article>()
}