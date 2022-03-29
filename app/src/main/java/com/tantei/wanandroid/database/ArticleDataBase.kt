package com.tantei.wanandroid.database.entities

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tantei.wanandroid.database.ArticleDao

@Database(entities = [Article::class], version = 1)
abstract class ArticleDataBase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}