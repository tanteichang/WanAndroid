package com.tantei.wanandroid.ui.home

import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tantei.wanandroid.R
import com.tantei.wanandroid.base.BaseFragmentVMVB
import com.tantei.wanandroid.databinding.FragmentArticleListBinding
import com.tantei.wanandroid.ui.home.adapter.ArticleAdapter
import com.tantei.wanandroid.ui.home.bean.Article
import com.tantei.wanandroid.ui.home.bean.Banner
import com.tantei.wanandroid.ui.home.viewModel.ArticleViewModel
import com.tantei.wanandroid.ui.web.WebFragmentArgs

private const val TAG = "ArticleListFragment"

class ArticleListFragment() : BaseFragmentVMVB<ArticleViewModel, FragmentArticleListBinding>(), ArticleAdapter.Callbacks {

    override val layoutId: Int
        get() = R.layout.fragment_article_list

    private lateinit var adapter: ArticleAdapter

    override fun initView() {
        val layoutManager = LinearLayoutManager(activity)
        mBinding.articleList.layoutManager = layoutManager
        adapter = ArticleAdapter(this, mViewModel.articleList, mViewModel.bannerList, this)
        mBinding.articleList.adapter = adapter

        mViewModel.getBannerList()
        mViewModel.getArticleList(0)
    }

    override fun initObserver() {
        mViewModel.articleListLiveData.observe(viewLifecycleOwner, Observer { result ->
            mViewModel.articleList.clear()
            mViewModel.articleList.addAll(result)
            adapter.notifyDataSetChanged()
        })

        mViewModel.bannerListLiveData.observe(viewLifecycleOwner) { result ->
            Log.d(TAG, "initObserver bannerList: ${result.size}")
            mViewModel.bannerList.clear()
            mViewModel.bannerList.addAll(result)
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