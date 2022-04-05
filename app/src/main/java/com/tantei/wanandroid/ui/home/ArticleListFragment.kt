package com.tantei.wanandroid.ui.home

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tantei.wanandroid.R
import com.tantei.wanandroid.base.BaseFragmentVMVB
import com.tantei.wanandroid.databinding.FragmentArticleListBinding
import com.tantei.wanandroid.network.ApiResult
import com.tantei.wanandroid.repositories.ArticleRepository
import com.tantei.wanandroid.ui.home.adapter.ArticleAdapter
import com.tantei.wanandroid.ui.home.bean.Article
import com.tantei.wanandroid.ui.home.bean.Banner
import com.tantei.wanandroid.ui.home.viewModel.ArticleViewModel
import com.tantei.wanandroid.ui.web.WebFragmentArgs
import com.tantei.wanandroid.utils.LLog
import com.tantei.views.TRecycleView
import kotlinx.coroutines.runBlocking

private const val TAG = "ArticleListFragment"

class ArticleListFragment() : BaseFragmentVMVB<ArticleViewModel, FragmentArticleListBinding>(),
    ArticleAdapter.Callbacks {

    override val layoutId: Int
        get() = R.layout.fragment_article_list

    private lateinit var adapter: ArticleAdapter

    private var currentPage = 1
    private var isLoadMore = false

    override fun initView() {
        val layoutManager = LinearLayoutManager(activity)
        mBinding.articleList.layoutManager = layoutManager
        adapter = ArticleAdapter(this, this)
        mBinding.articleList.adapter = adapter

        if (mViewModel.articles.isEmpty() == true) {
            LLog.d("init article $currentPage")
            mViewModel.getArticleList(1)
        }
        if (mViewModel.bannerList.value?.isEmpty() == true) {
            mViewModel.getBannerList()
        }

        mBinding.homeSwipeRefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                mViewModel.getArticleList(1)
                mBinding.homeSwipeRefresh.isRefreshing = false
            }
        })
        mBinding.articleList.setOnScrollBottomListener(object : TRecycleView.OnScrollBottomListener {
            override fun onScrollBottom() {
                super.onScrollBottom()
                if (!isLoadMore) {
                    isLoadMore = true
                    currentPage++
                    mBinding.homeSwipeRefresh.isRefreshing = true
                    mViewModel.getArticleList(currentPage)
                }
            }
        })
    }


    override fun initObserver() {
        mViewModel.articleList.observe(viewLifecycleOwner, Observer { result ->
            LLog.d("article change $result")
            mBinding.homeSwipeRefresh.isRefreshing = false
            if (currentPage == 1) {
                mViewModel.articles.addAll(result)
                LLog.d("${mViewModel.articles}")
                adapter.setArticleList(mViewModel.articles)
                adapter.notifyDataSetChanged()
            } else  {
              if (isLoadMore) {
                  val positionStart = mViewModel.articles.size
                  mViewModel.articles.plusAssign(result)
                  adapter.setArticleList(mViewModel.articles)
                  val itemCount = mViewModel.articles.size - positionStart
                  adapter.notifyItemRangeChanged(currentPage + 1, itemCount)
                  isLoadMore = false
              }
            }

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

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
         isLoadMore = true
        LLog.d("onPautse")
    }
}