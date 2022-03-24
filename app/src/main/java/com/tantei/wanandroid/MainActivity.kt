package com.tantei.wanandroid

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.tantei.wanandroid.base.BaseActivity
import com.tantei.wanandroid.databinding.ActivityMainBinding
import com.tantei.wanandroid.models.ArticleListResponse
import com.tantei.wanandroid.network.Api
import com.tantei.wanandroid.network.ServiceCreator
import com.tantei.wanandroid.ui.pages.HomeFragment
import com.tantei.wanandroid.ui.pages.MineFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentPage: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()

        val homeService = ServiceCreator.create(Api::class.java)

        homeService.fetchArticleList().enqueue(object : Callback<ArticleListResponse> {
            override fun onFailure(call: Call<ArticleListResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

            override fun onResponse(call: Call<ArticleListResponse>, response: Response<ArticleListResponse>) {
                Log.d(TAG, "onResponse: ${response.body()?.data?.articleList}")
            }
        })

    }

    private fun replaceFragment(id: Int, fragment: Fragment) {
        if (currentPage == id) {
            return
        }
        currentPage = id
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_content, fragment)
            addToBackStack(null)
            commit()
        }
    }

    private fun initBottomNavigation() {
        replaceFragment(R.id.page_home, HomeFragment())
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.page_home -> {
                    replaceFragment(R.id.page_home, HomeFragment())
                    true
                }
                R.id.page_mine -> {
                    replaceFragment(R.id.page_mine, MineFragment())
                    true
                }
                else -> false
            }
        }
    }
}