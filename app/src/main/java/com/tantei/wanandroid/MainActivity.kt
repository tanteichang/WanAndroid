package com.tantei.wanandroid

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelStore
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.tantei.wanandroid.base.BaseActivity
import com.tantei.wanandroid.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

data class Page(
    val id: Int,
    val title: String,
)

class MainActivity : BaseActivity() {
    companion object {
        var myViewModelStore: ViewModelStore? = null
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var mAppBarConfiguration: AppBarConfiguration
    private lateinit var pageMap: Map<Int, Page>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelLazy(MainViewModel::class, { viewModelStore}, { defaultViewModelProviderFactory }).value
        myViewModelStore = viewModelStore

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pageMap = mapOf<Int, Page>(
            R.id.page_home to Page(R.id.page_home, getString(R.string.home)),
            R.id.page_mine to Page(R.id.page_mine, getString(R.string.mine))
        )

        initToolBar()
        initBottomNavigation()
        initObserver()
    }

    private fun initObserver() {
        // 显示/隐藏底部导航栏
        viewModel.isBottomNavHide.observe(this) {
            binding.bottomNavigation.visibility = if (it) View.GONE else View.VISIBLE
        }
        // 设置顶部标题
        viewModel.toolbarTitle.observe(this) { binding.toolBar.title = it }
    }

    private fun initToolBar() {
        viewModel.toolbarTitle.value = resources.getString(R.string.home)
        val host  = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = host.findNavController()
        mAppBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = pageMap.keys,
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        setSupportActionBar(binding.toolBar)
        setupActionBarWithNavController(navController, mAppBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(
            mAppBarConfiguration
        )
    }

    private fun initBottomNavigation() {
        val host  = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = host.findNavController()
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            run {
                binding.toolBar.title = pageMap[destination.id]?.title
            }
        }
    }

}