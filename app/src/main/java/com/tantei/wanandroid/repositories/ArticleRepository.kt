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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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

    suspend fun getArticleList(page: Int) = WanNetwork.fetchArticleList(page)

    suspend fun getBannerList() = WanNetwork.fetchBannerList()


//    suspend fun getArticleList(page: Int, withTop: Boolean = false): ApiResult<BaseResponse<ArticleListResponse>> {
//        // https://developer.android.com/jetpack/guide/data-layer#in-memory-cache
//
////        if (withTop && lastTopArticleList.isEmpty()) {
////            GlobalScope.launch {
////                WanNetwork.fetchTopArticleList()
////                when(val result = WanNetwork.fetchTopArticleList()) {
////                    is ApiResult.Success -> {
////
////                    }
////                    is ApiResult.Failure -> {
////
////                    }
////                }
////            }
////        }
//
////        val articleListResponse = WanNetwork.fetchArticleList(page)
////        if (articleListResponse.errorCode == CODE.OK.code) {
////            val articleList = if (withTop) {
////                val joined = ArrayList<Article>()
////                joined.addAll(lastTopArticleList)
////                joined.addAll(articleListResponse.data.articleList)
////                joined
////            } else {
////                articleListResponse.data.articleList
////            }
////            Result.success(articleList)
////        } else {
////            Result.failure((RuntimeException("response code is${articleListResponse.errorCode}")))
////        }
//        return WanNetwork.fetchArticleList(page)
//    }

}