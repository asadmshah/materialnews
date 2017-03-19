package com.asadmshah.materialnews.screens.page

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.asadmshah.materialnews.R
import com.asadmshah.materialnews.screens.base.findView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class BasicViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val dateFormatter = SimpleDateFormat.getDateTimeInstance()

    private val articleImage: ImageView = view.findView(R.id.article_image)
    private val articleTitle: TextView = view.findView(R.id.article_title)
    private val articleDate: TextView = view.findView(R.id.article_date)

    fun setImage(url: String) {
        Glide.with(itemView.context).load(url).into(articleImage)
    }

    fun setTitle(title: String) {
        articleTitle.text = title
    }

    fun setDate(date: Date?) {
        date?.let { articleDate.text = dateFormatter.format(it) }
    }

}