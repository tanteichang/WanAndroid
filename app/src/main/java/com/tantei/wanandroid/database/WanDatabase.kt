package com.tantei.wanandroid.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tantei.wanandroid.database.entities.Article
import com.tantei.wanandroid.repositories.ArticleRepository
import java.lang.IllegalStateException

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class WanDatabase : RoomDatabase() {
    companion object {
        private var INSTANCE: WanDatabase? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, WanDatabase::class.java, "wan-database").build()
            }
        }
        fun get(): WanDatabase {
            return INSTANCE ?: throw IllegalStateException("WanDatabase must be initialized")
        }
    }

    abstract fun articleDao(): ArticleDao
}