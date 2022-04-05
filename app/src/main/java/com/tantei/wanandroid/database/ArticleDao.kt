package com.tantei.wanandroid.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tantei.wanandroid.database.entities.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(article: Article)

    @Query("SELECT * FROM article LIMIT :pageSize OFFSET :page * :pageSize")
    fun getArticles(page: Int = 0, pageSize: Int = 20): List<Article>
}