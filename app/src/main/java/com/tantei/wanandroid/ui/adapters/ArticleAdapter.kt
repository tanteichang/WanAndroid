package com.tantei.wanandroid.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.tantei.wanandroid.R
import com.tantei.wanandroid.models.Article
import com.tantei.wanandroid.ui.fragments.ArticleListFragment
import org.w3c.dom.Text
import java.text.DateFormat
import java.time.format.DateTimeFormatter
import java.util.*

private const val TAG = "ArticleAdapter"

class ArticleAdapter(private val fragment: Fragment, private val articleList: List<Article>, val callbacks: Callbacks) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    interface Callbacks {
        fun onArticleTitleClick(article: Article)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleTitle: TextView = view.findViewById(R.id.article_title)
        val shareUser: TextView = view.findViewById(R.id.shareUser)
        val publishTime: TextView = view.findViewById(R.id.publishTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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

    override fun getItemCount(): Int {
        return articleList.size
    }
}