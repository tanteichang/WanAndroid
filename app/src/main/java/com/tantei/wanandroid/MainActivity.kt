package com.tantei.wanandroid

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.tantei.wanandroid.base.BaseActivity
import com.tantei.wanandroid.databinding.ActivityMainBinding
import com.tantei.wanandroid.ui.home.HomeFragment
import com.tantei.wanandroid.ui.mine.MineFragment
import com.tantei.wanandroid.viewmodels.GlobalViewModel

private const val TAG = "MainActivity"

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentPage: Int = -1
    private val globalViewModel by viewModels<GlobalViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()
        initObserver()
    }

    private fun initObserver() {
        globalViewModel.bottomNavigationHide.observe(this, Observer {
            Log.d(TAG, "initObserver: $it")
            if (it == true) {
                binding.bottomNavigation.visibility =View.GONE
            } else {
                binding.bottomNavigation.visibility =View.VISIBLE
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