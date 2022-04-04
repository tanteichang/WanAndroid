package com.tantei.wanandroid.repositories

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.tantei.wanandroid.base.BaseRepository
import com.tantei.wanandroid.ui.home.bean.Article
import com.tantei.wanandroid.base.BaseResponse
import com.tantei.wanandroid.network.ApiResult
import com.tantei.wanandroid.network.CODE
import com.tantei.wanandroid.network.WanNetwork
import com.tantei.wanandroid.ui.home.bean.ArticleListResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import java.lang.IllegalStateException
import kotlin.coroutines.CoroutineContext
import kotlin.math.log
import kotlin.system.measureTimeMillis

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

    suspend fun getArticleList(page: Int) = WanNetwork.fetchArticleList(page)

    suspend fun getBannerList() = WanNetwork.fetchBannerList()

    suspend fun getArticleWithTop(page: Int): ApiResult<List<Article>> {
        return coroutineScope {
            val result = ArrayList<Article>()
            val aList = ArrayList<Article>()
            val tList = ArrayList<Article>()
            val fetchArticle = async { WanNetwork.fetchArticleList(page) }
            val fetchTop = async { WanNetwork.fetchTopArticleList() }

            when(val articles = fetchArticle.await()) {
                is ApiResult.Success -> {
                    articles.result?.data?.articleList?.let { aList.addAll(it) }
                }
                is ApiResult.Failure -> ApiResult.Failure(404, "fetchArticleList error")
            }
            when(val topRes = fetchTop.await()) {
                is ApiResult.Success -> {
                    topRes.result?.data?.let { tList.addAll(it) }
                }
                is ApiResult.Failure ->  ApiResult.Failure(404, "fetchTopArticleList error")
            }
            result.apply {
                addAll(tList)
                addAll(aList)
            }
            ApiResult.Success(result)
        }
    }


}