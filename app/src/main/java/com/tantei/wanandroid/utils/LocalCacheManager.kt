package com.tantei.wanandroid.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.Nullable
import com.google.gson.Gson
import java.lang.reflect.Type



object LocalCacheManager {


    lateinit var sp: SharedPreferences

    fun init(context: Application, name: String) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    // TODO 实现存储 Object
    fun <T>addCache(key: String, value: T): Boolean {
        if (value == null) return  false

       try {
           val editer = sp.edit()
           when(value) {
               is String -> editer.putString(key, value)
               is Boolean -> editer.putBoolean(key, value)
               is Int -> editer.putInt(key, value)
               is Float -> editer.putFloat(key, value)
               is Long -> editer.putLong(key, value)
               is Set<*> -> editer.putStringSet(key, value as Set<String>)
//            is Object -> editer.putString(key, serialize(value))
           }
           editer.commit()
           return true
       } catch(e: Exception) {
           return  false
       }
    }

    // TODO 实现获取 object
    fun <T> getCache(key: String, @Nullable default: T, @Nullable type: Type) {
        when(default) {
            is String ->  sp.getString(key, default) as T
            is Boolean ->  sp.getBoolean(key, default) as T
            is Int ->  sp.getInt(key, default) as T
            is Float ->  sp.getFloat(key, default) as T
            is Long -> sp.getLong(key, default) as T
            is Set<*> ->  sp.getStringSet(key, default as Set<String>) as T
            else -> null
        }
    }

   private fun serialize(value: Object): String {
       val gson = Gson()
       val jsonStr = gson.toJson(value)
       return jsonStr
   }
    private fun <T> deSerialization(value: String,clazz: Class<T>): T {
        val gson = Gson()
        val josnObject = gson.fromJson(value, clazz)
        return josnObject
    }

}

