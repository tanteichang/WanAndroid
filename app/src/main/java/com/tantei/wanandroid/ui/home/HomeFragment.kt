package com.tantei.wanandroid.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.tantei.wanandroid.R
import com.tantei.wanandroid.base.BaseFragment
import com.tantei.wanandroid.base.BaseFragmentVMVB
import com.tantei.wanandroid.databinding.FragmentHomeBinding
import com.tantei.wanandroid.ui.home.bean.Article
import com.tantei.wanandroid.ui.home.viewModel.HomeViewModel
import com.tantei.wanandroid.ui.web.WebFragmentArgs
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.listener.OnPageChangeListener

private const val TAG = "HomeFragment"

class HomeFragment : BaseFragmentVMVB<HomeViewModel, FragmentHomeBinding>(){


    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun initView() {

    }
    override fun initObserver() {
    }
}