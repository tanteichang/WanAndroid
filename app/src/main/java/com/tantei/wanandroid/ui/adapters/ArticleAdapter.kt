package com.tantei.wanandroid.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.tantei.wanandroid.R
import com.tantei.wanandroid.models.Article

class ArticleAdapter(private val fragment: Fragment, private val articleList: List<Article>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleTitle = view.findViewById<TextView>(R.id.article_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articleList[position]
        holder.articleTitle.text = article.title
    }

    override fun getItemCount(): Int {
        return articleList.size
    }
}