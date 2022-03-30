package com.tantei.wanandroid.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tantei.wanandroid.R
import com.tantei.wanandroid.ui.home.bean.Article
import com.tantei.wanandroid.ui.web.WebFragmentArgs
import com.youth.banner.Banner
import com.tantei.wanandroid.ui.home.bean.Banner as BannerBean
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import java.text.DateFormat
import java.util.*

private const val TAG = "ArticleAdapter"

class ArticleAdapter(
    private val fragment: Fragment,
    private val articleList: List<Article>,
    private val bannerList: List<BannerBean>,
    val callbacks: Callbacks) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class VIEW_TYPE(value: Int) {
        BANNER(1),ARTICLE(2)
    }

    interface Callbacks {
        fun onArticleTitleClick(article: Article)
    }

    inner class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleTitle: TextView = view.findViewById(R.id.article_title)
        val shareUser: TextView = view.findViewById(R.id.shareUser)
        val publishTime: TextView = view.findViewById(R.id.publishTime)
    }

    inner class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val banner = view.findViewById<Banner<List<BannerBean>, BannerImageAdapter<List<BannerBean>>>>(R.id.home_item_banner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == VIEW_TYPE.BANNER.ordinal) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_item, parent, false)
            return BannerViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
            return ArticleViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return VIEW_TYPE.BANNER.ordinal
        } else {
            return VIEW_TYPE.ARTICLE.ordinal
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ArticleViewHolder) {
            val article = articleList[position]
            holder.articleTitle.text = article.title
            holder.shareUser.text = article.shareUser
            holder.publishTime.text = DateFormat.getDateInstance().format(Date(article.publishTime))

            holder.articleTitle.setOnClickListener {
                callbacks.onArticleTitleClick(article)
            }
            holder.shareUser.setOnClickListener {
            }
        }
        if (holder is BannerViewHolder) {
            holder.banner.setAdapter(object : BannerImageAdapter<List<BannerBean>>(bannerList) {
                override fun onBindView(
                    holder: BannerImageHolder?,
                    data: List<BannerBean>?,
                    position: Int,
                    size: Int
                ) {
                    holder?.let { Glide.with(fragment).load(data).into(it.imageView) }
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }
}