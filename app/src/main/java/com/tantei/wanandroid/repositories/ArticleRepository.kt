package com.tantei.wanandroid.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import com.tantei.wanandroid.models.Article
import com.tantei.wanandroid.network.WanNetwork
import kotlinx.coroutines.Dispatchers

object ArticleRepository {
    fun getArticleList(page: Int): LiveData<List<Article>> {

    }
}