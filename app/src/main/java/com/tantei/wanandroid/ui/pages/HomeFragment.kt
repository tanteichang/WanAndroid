package com.tantei.wanandroid.ui.pages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.stx.xhb.androidx.XBanner
import com.tantei.wanandroid.R
import com.tantei.wanandroid.base.BaseFragment
import com.tantei.wanandroid.databinding.FragmentHomeBinding
import com.tantei.wanandroid.models.Article
import com.tantei.wanandroid.models.Banner
import com.tantei.wanandroid.ui.fragments.ArticleDetailFragment
import com.tantei.wanandroid.ui.fragments.ArticleListFragment
import com.tantei.wanandroid.viewmodels.HomeViewModel
import kotlin.math.log

private const val TAG = "HomeFragment"

class HomeFragment : BaseFragment<HomeViewModel>() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var articleListFragment: ArticleListFragment
    private lateinit var articleDetailFragment: ArticleDetailFragment
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        
        val childFragmentManager = childFragmentManager

        articleListFragment = ArticleListFragment(object : ArticleListFragment.OnItemClick {
            override fun onArticleClick(article: Article) {
                childFragmentManager.beginTransaction()
                    .add(R.id.frame_article_list, ArticleDetailFragment())
                    .addToBackStack(null)
                    .commit()
            }
        })

        childFragmentManager.beginTransaction()
            .add(R.id.frame_article_list, articleListFragment)
            .addToBackStack(null)
            .commit()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.bannerList.observe(viewLifecycleOwner, { result ->
            Log.d(TAG, "onViewCreated: $result")

            Glide.with(this@HomeFragment).load(result.getOrNull()?.get(0)?.imagePath).into(binding.imageView)

            binding.xbanner.loadImage(object : XBanner.XBannerAdapter{
                override fun loadBanner(banner: XBanner?, model: Any?, view: View?, position: Int) {
                    Glide.with(this@HomeFragment).load(result.getOrNull()?.get(0)?.imagePath).into(view as ImageView)
                }
            })

        })
    }
}