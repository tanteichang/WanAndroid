package com.tantei.wanandroid.repositories

import androidx.lifecycle.liveData
import com.tantei.wanandroid.base.BaseRepository
import com.tantei.wanandroid.models.Article
import com.tantei.wanandroid.network.CODE
import com.tantei.wanandroid.network.WanNetwork
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

private const val TAG = "ArticleRepository"

object ArticleRepository : BaseRepository() {
    fun getArticleList(page: Int) = fire(Dispatchers.IO) {
        val articleListResponse = WanNetwork.fetchArticleList(page)
        if (articleListResponse.errorCode == CODE.OK.code) {
            val articleList = articleListResponse.data.articleList
            Result.success(articleList)
        } else {
            Result.failure((RuntimeException("response code is${articleListResponse.errorCode}")))
        }
    }
}