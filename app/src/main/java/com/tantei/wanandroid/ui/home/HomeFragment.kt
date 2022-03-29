package com.tantei.wanandroid.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.tantei.wanandroid.R
import com.tantei.wanandroid.base.BaseFragment
import com.tantei.wanandroid.databinding.FragmentHomeBinding
import com.tantei.wanandroid.ui.home.bean.Article
import com.tantei.wanandroid.ui.home.viewModel.HomeViewModel
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder

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
                val bundle: Bundle
                childFragmentManager.beginTransaction()
                    .add(R.id.frame_article_detail, ArticleDetailFragment.newInstance(article.link))
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


        viewModel.bannerList.observe(viewLifecycleOwner) { result ->
            Log.d(TAG, "onViewCreated: ${result.getOrNull()?.get(0)?.imagePath}")

            val imageUrls = result.getOrNull()?.map {
                it.imagePath
            }

            binding.homeBanner.setAdapter(object : BannerImageAdapter<String>(imageUrls) {
                override fun onBindView(
                    holder: BannerImageHolder?,
                    data: String?,
                    position: Int,
                    size: Int
                ) {
                    if (holder != null) {
                        Glide.with(this@HomeFragment).load(data).into(holder.imageView)
                    }
                }
            })

        }
    }
}