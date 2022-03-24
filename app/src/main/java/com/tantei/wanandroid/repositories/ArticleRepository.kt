package com.tantei.wanandroid.repositories

import androidx.lifecycle.liveData
import com.tantei.wanandroid.models.Article
import com.tantei.wanandroid.network.CODE
import com.tantei.wanandroid.network.WanNetwork
import kotlinx.coroutines.Dispatchers

object ArticleRepository {
    fun getArticleList(page: Int) = liveData(Dispatchers.IO) {
        val result = try {
            val articleListResponse = WanNetwork.fetchArticleList(page)
            if (articleListResponse.errorCode == CODE.OK.code) {
                val articleList = articleListResponse.data.articleList
                Result.success(articleList)
            } else {
                Result.failure((RuntimeException("response code is${articleListResponse.errorCode}")))
            }
        } catch (e: Exception) {
            Result.failure<List<Article>>(e)
        }
        emit(result)
    }
}