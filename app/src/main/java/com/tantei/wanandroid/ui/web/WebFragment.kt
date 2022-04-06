package com.tantei.wanandroid.ui.web

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.tantei.wanandroid.R
import com.tantei.wanandroid.base.BaseFragmentVMVB
import com.tantei.wanandroid.databinding.FragmentWebBinding

class WebFragment() : BaseFragmentVMVB<WebViewModel, FragmentWebBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_web
    override val hiddenBottomNavigation: Boolean
        get() = true

    private lateinit var args: WebFragmentArgs


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = arguments?.let { WebFragmentArgs.fromBundle(it) }!!
    }

    override fun initView() {
        globalViewModel.toolbarTitle.value = args.title


        mBinding.webWebview.run {
            settings.javaScriptEnabled = true
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    mViewModel.progress.value = newProgress
                }
            }
            loadUrl(args.url)
        }
    }

    override fun initObserver() {
        mViewModel.progress.observe(this) {
            if (it == 100) {
                mBinding.webProgress.visibility =  View.GONE
            } else {
                mBinding.webProgress.visibility = View.VISIBLE
                mBinding.webProgress.progress = it
            }
        }
    }
}