package com.tantei.wanandroid.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tantei.wanandroid.R


private const val ARG_LINK = "link"

class ArticleDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var link: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            link = it.getString(ARG_LINK)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article_detail, container, false)
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