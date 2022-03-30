package com.tantei.wanandroid.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tantei.wanandroid.R
import com.tantei.wanandroid.base.BaseFragment
import com.tantei.wanandroid.base.BaseFragmentVMVB
import com.tantei.wanandroid.databinding.FragmentArticleListBinding
import com.tantei.wanandroid.ui.home.adapter.ArticleAdapter
import com.tantei.wanandroid.ui.home.bean.Article
import com.tantei.wanandroid.ui.home.viewModel.ArticleViewModel
import com.tantei.wanandroid.ui.web.WebFragmentArgs
import com.tantei.wanandroid.viewmodels.GlobalViewModel

private const val TAG = "ArticleListFragment"

class ArticleListFragment() : BaseFragmentVMVB<ArticleViewModel, FragmentArticleListBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_article_list


    private lateinit var adapter: ArticleAdapter

    override fun initView() {
        val layoutManager = LinearLayoutManager(activity)
        mBinding.articleList.layoutManager = layoutManager
        adapter = ArticleAdapter(this, mViewModel.articleList, object : ArticleAdapter.Callbacks {
            override fun onArticleTitleClick(article: Article) {
                val bundle = WebFragmentArgs(article.link, article.title).toBundle()
                findNavController().navigate(R.id.action_home_to_web, bundle)
            }
        })
        Log.d(TAG, "initView: call")
        mBinding.articleList.adapter = adapter
        if (mViewModel.articleList.isEmpty()) {
            Log.d(TAG, "initView: fetch")
            mViewModel.getArticleList(0)
        }
    }

    override fun initObserver() {

        mViewModel.articleListData.observe(viewLifecycleOwner, Observer { result ->
            Log.d(TAG, "onActivityCreated: articleListData ober $result")
            val articleList = result.getOrNull()
            if (articleList != null) {
                mViewModel.articleList.clear()
                Log.d(TAG, "onActivityCreated: add articleList ${articleList[0]}")
                mViewModel.articleList.addAll(articleList)
                adapter.notifyDataSetChanged()
            }
        })
    }


}