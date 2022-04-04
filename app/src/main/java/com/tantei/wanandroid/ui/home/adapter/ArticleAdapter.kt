package com.tantei.wanandroid.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
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
import com.youth.banner.listener.OnBannerListener
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

private const val TAG = "ArticleAdapter"

class ArticleAdapter(
    private val fragment: Fragment,
    val callbacks: Callbacks) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var articleList: ArrayList<Article> = ArrayList()
    private var bannerList: ArrayList<BannerBean> = ArrayList()
    fun setArticleList(list: List<Article>) {
        articleList.clear()
        articleList.addAll(list)
    }
    fun appendArticleList(list: List<Article>) {
        articleList.addAll(list)
    }
    fun setBannerList(list: List<BannerBean>) {
        bannerList.clear()
        bannerList.addAll(list)
    }


    // 文章列表，banner（1）,footer(1)
    private val listSize: Int
        get() {
            return articleList.size + (if (bannerList.isEmpty()) 0 else 1)
        }

    enum class VIEW_TYPE(val viewType: Int) {
        BANNER(1),ARTICLE(2),FOOTER(3)
    }

    interface Callbacks {
        fun onArticleTitleClick(article: Article)
        fun onBannerItemClick(banner: BannerBean)
    }

    inner class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleTitle: TextView = view.findViewById(R.id.article_title)
        val shareUser: TextView = view.findViewById(R.id.shareUser)
        val publishTime: TextView = view.findViewById(R.id.publishTime)
    }

    inner class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val banner = view.findViewById<Banner<BannerBean, BannerImageAdapter<BannerBean>>>(R.id.home_item_banner)
    }

    inner class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val process = view.findViewById<ProgressBar>(R.id.bottom_progress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE.BANNER.viewType -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_item, parent, false)
                return BannerViewHolder(view)
            }
            VIEW_TYPE.ARTICLE.viewType -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
                return ArticleViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.footer_item, parent, false)
                return FooterViewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (bannerList.isNotEmpty() && position == 0) {
            return VIEW_TYPE.BANNER.viewType
        } else {
            return VIEW_TYPE.ARTICLE.viewType
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ArticleViewHolder) {
            val _position = getRealPosition(holder)
            val article = articleList[_position]
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
            holder.banner.setAdapter(object : BannerImageAdapter<BannerBean>(bannerList) {
                override fun onBindView(
                    holder: BannerImageHolder?,
                    data: BannerBean?,
                    position: Int,
                    size: Int
                ) {
                    holder?.let { Glide.with(fragment).load(data?.imagePath).into(it.imageView) }
                }
            })
            holder.banner.setOnBannerListener(object : OnBannerListener<BannerBean> {
                override fun OnBannerClick(
                    data: com.tantei.wanandroid.ui.home.bean.Banner?,
                    position: Int
                ) {
                    callbacks.onBannerItemClick(data!!)
                }
            })
        }
        if (holder is FooterViewHolder) {
            holder.process.progress = 100
        }
    }

    // 计算存在 banner 之后的 position
    private fun getRealPosition(holder: RecyclerView.ViewHolder): Int {
        val position = holder.layoutPosition
        return position - if (bannerList.isEmpty()) 0 else 1
    }

    override fun getItemCount(): Int {
        return listSize
    }
}