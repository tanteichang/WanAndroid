package com.tantei.wanandroid

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.tantei.wanandroid.base.BaseActivity
import com.tantei.wanandroid.databinding.ActivityMainBinding
import com.tantei.wanandroid.ui.home.HomeFragment
import com.tantei.wanandroid.ui.mine.MineFragment
import com.tantei.wanandroid.viewmodels.GlobalViewModel

private const val TAG = "MainActivity"

class MainActivity : BaseActivity() {
    companion object {
        var myViewModelStore: ViewModelStore? = null
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelLazy(MainViewModel::class, { viewModelStore}, { defaultViewModelProviderFactory }).value
        myViewModelStore = viewModelStore

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolBar()
        initBottomNavigation()
        initObserver()
    }

    private fun initObserver() {
        viewModel.isBottomNavHide.observe(this) {
            binding.bottomNavigation.visibility = if (it) View.GONE else View.VISIBLE
        }
        viewModel.toolbarTitle.observe(this) {
            binding.toolBar.title = it
        }
    }

    private fun initToolBar() {
        viewModel.toolbarTitle.value = resources.getString(R.string.home)
        setSupportActionBar(binding.toolBar)
    }
    private fun initBottomNavigation() {
        val host  = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = host.findNavController()
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            run {
                when (destination.id) {
                    R.id.page_mine -> {
                        binding.toolBar.title = resources.getString(R.string.mine)
                    }
                    R.id.page_home -> {
                        binding.toolBar.title = resources.getString(R.string.home)
                    }
                }
            }
        }
    }

}