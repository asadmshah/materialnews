package com.asadmshah.materialnews.screens.page

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.asadmshah.materialnews.R
import com.asadmshah.materialnews.screens.base.findView
import java.text.SimpleDateFormat
import java.util.*

class HeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val dateFormatter = SimpleDateFormat.getDateTimeInstance()

    private val publisherTitle: TextView = view.findView(R.id.publisher_title)
    private val articleTitle: TextView = view.findView(R.id.article_title)
    private val articleDate: TextView = view.findView(R.id.article_date)

    fun setPublisherTitle(title: String) {
        publisherTitle.text = title
    }

    fun setArticleTitle(title: String) {
        articleTitle.text = title
    }

    fun setArticleDate(date: Date?) {
        date?.let { articleDate.text = dateFormatter.format(it) }
    }

}