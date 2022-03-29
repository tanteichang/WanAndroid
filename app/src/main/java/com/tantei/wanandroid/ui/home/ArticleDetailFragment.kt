package com.tantei.wanandroid.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.tantei.wanandroid.R
import com.tantei.wanandroid.databinding.FragmentArticleDetailBinding
import com.tantei.wanandroid.databinding.FragmentArticleListBinding


private const val ARG_LINK = "link"

class ArticleDetailFragment : Fragment() {
    private var link: String? = null

    private lateinit var binding: FragmentArticleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            link = it.getString(ARG_LINK)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleDetailBinding.inflate(inflater, container, false)

        binding.articleDetailWebview.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            link?.let { loadUrl(it) }
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(link: String) =
            ArticleDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_LINK, link)
                }
            }
    }
}