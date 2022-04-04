package com.tantei.wanandroid.ui.home

import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tantei.wanandroid.R
import com.tantei.wanandroid.base.BaseFragmentVMVB
import com.tantei.wanandroid.databinding.FragmentArticleListBinding
import com.tantei.wanandroid.repositories.ArticleRepository
import com.tantei.wanandroid.ui.home.adapter.ArticleAdapter
import com.tantei.wanandroid.ui.home.bean.Article
import com.tantei.wanandroid.ui.home.bean.Banner
import com.tantei.wanandroid.ui.home.viewModel.ArticleViewModel
import com.tantei.wanandroid.ui.web.WebFragmentArgs
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val TAG = "ArticleListFragment"

class ArticleListFragment() : BaseFragmentVMVB<ArticleViewModel, FragmentArticleListBinding>(), ArticleAdapter.Callbacks {

    override val layoutId: Int
        get() = R.layout.fragment_article_list

    private lateinit var adapter: ArticleAdapter

    override fun initView() {
        val layoutManager = LinearLayoutManager(activity)
        mBinding.articleList.layoutManager = layoutManager
        adapter = ArticleAdapter(this, this)
        mBinding.articleList.adapter = adapter

        mViewModel.getBannerList()
        mViewModel.getArticleList(0)

        mBinding.homeSwipeRefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                mViewModel.getArticleList(0)
                mBinding.homeSwipeRefresh.isRefreshing = false
            }
        })
    }

    override fun initObserver() {
        mViewModel.articleList.observe(viewLifecycleOwner, Observer { result ->
            adapter.setArticleList(result)
            adapter.notifyDataSetChanged()
        })

        mViewModel.bannerList.observe(viewLifecycleOwner) { result ->
            adapter.setBannerList(result)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onArticleTitleClick(article: Article) {
        val bundle = WebFragmentArgs(article.link, article.title).toBundle()
        findNavController().navigate(R.id.action_home_to_web, bundle)
    }

    override fun onBannerItemClick(banner: Banner) {
        val bundle = WebFragmentArgs(banner.url, banner.title).toBundle()
        findNavController().navigate(R.id.action_home_to_web, bundle)
    }
}