package com.tantei.wanandroid.repositories

import android.content.Context
import androidx.room.Room
import com.tantei.wanandroid.base.BaseRepository
import com.tantei.wanandroid.ui.home.bean.Article
import com.tantei.wanandroid.base.BaseResponse
import com.tantei.wanandroid.network.CODE
import com.tantei.wanandroid.network.WanNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import java.lang.IllegalStateException
import kotlin.coroutines.CoroutineContext

private const val TAG = "ArticleRepository"

private const val DATABASE_NAME = "article-database"

class ArticleRepository private constructor(context: Context): BaseRepository() {
    companion object {
        private var INSTANCE: ArticleRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ArticleRepository(context)
            }
        }
        fun get(): ArticleRepository {
            return INSTANCE ?: throw IllegalStateException("ArticleRepository must be initialized")
        }
    }
    private val latestNewsMutex = Mutex()

    private val lastTopArticleList = mutableListOf<Article>()

    fun getArticleList(page: Int, withTop: Boolean = false) = fire(Dispatchers.IO) {
        // https://developer.android.com/jetpack/guide/data-layer#in-memory-cache

        if (withTop && lastTopArticleList.isEmpty()) {
            var articleTopResponse =  WanNetwork.fetchTopArticleList()
            if (articleTopResponse.errorCode == CODE.OK.code) {
                lastTopArticleList.addAll(articleTopResponse.data)
            }
        }

        val articleListResponse = WanNetwork.fetchArticleList(page)
        if (articleListResponse.errorCode == CODE.OK.code) {
            val articleList = if (withTop) {
                val joined = ArrayList<Article>()
                joined.addAll(lastTopArticleList)
                joined.addAll(articleListResponse.data.articleList)
                joined
            } else {
                articleListResponse.data.articleList
            }
            Result.success(articleList)
        } else {
            Result.failure((RuntimeException("response code is${articleListResponse.errorCode}")))
        }
    }

    fun getTopArticleList() = fire(Dispatchers.IO) {
        if (!lastTopArticleList.isEmpty()) {
            Result.success(lastTopArticleList)
        }
        val res = WanNetwork.fetchTopArticleList()
        if (res.errorCode == CODE.OK.code) {
            lastTopArticleList.addAll(res.data)
            Result.success(lastTopArticleList)
        } else {
            Result.failure((RuntimeException("response code is${res.errorCode}")))
        }
    }

}