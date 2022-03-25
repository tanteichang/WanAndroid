package com.tantei.wanandroid.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tantei.wanandroid.databinding.FragmentArticleListBinding
import com.tantei.wanandroid.models.Article
import com.tantei.wanandroid.ui.adapters.ArticleAdapter
import com.tantei.wanandroid.viewmodels.ArticleViewModel

private const val TAG = "ArticleListFragment"

class ArticleListFragment(val onItemClick: OnItemClick) : Fragment() {

    interface OnItemClick {
        fun onArticleClick(article: Article)
    }

    val articleViewModel by lazy { ViewModelProvider(this).get(ArticleViewModel::class.java) }
    private lateinit var adapter: ArticleAdapter
    private lateinit var binding: FragmentArticleListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        binding.articleList.layoutManager = layoutManager
        adapter = ArticleAdapter(this, articleViewModel.articleList, object : ArticleAdapter.Callbacks {
            override fun onArticleTitleClick(article: Article) {
                Log.d(TAG, "onArticleTitleClick: $article")
//                onItemClick.onArticleClick(article)
            }
        })
        binding.articleList.adapter = adapter
        articleViewModel.getArticleList(0)

        articleViewModel.articleListData.observe(viewLifecycleOwner, Observer { result ->
            val articleList = result.getOrNull()
            if (articleList != null) {
                articleViewModel.articleList.clear()
                Log.d(TAG, "onActivityCreated: add articleList ${articleList.size}")
                articleViewModel.articleList.addAll(articleList)
                adapter.notifyDataSetChanged()
            }
        })
    }
}