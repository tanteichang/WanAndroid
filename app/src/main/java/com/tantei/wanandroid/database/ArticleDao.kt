package com.tantei.wanandroid.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.tantei.wanandroid.database.entities.Article

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getArticles(): LiveData<List<Article>>
}