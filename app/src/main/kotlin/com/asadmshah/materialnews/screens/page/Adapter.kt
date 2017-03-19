package com.asadmshah.materialnews.screens.page

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.asadmshah.materialnews.R

class Adapter(private val presenter: PageContract.Presenter) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HERO = 1
        private const val TYPE_BASIC = 2
    }

    private lateinit var recyclerView: RecyclerView

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    override fun getItemViewType(position: Int): Int {
        return when (presenter.getItemViewType(position)) {
            HeroViewHolder::class.java -> TYPE_HERO
            BasicViewHolder::class.java -> TYPE_BASIC
            else -> super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            TYPE_HERO -> {
                val view = inflater.inflate(R.layout.viewholder_hero, parent, false)

                recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    private var position = 0f
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        position += dy
                        val y = Math.min(position / view.height, 1f)
                        view.alpha = 1-y*3
                    }
                })

                HeroViewHolder(view)
            }
            TYPE_BASIC -> {
                val view = inflater.inflate(R.layout.viewholder_basic, parent, false)
                BasicViewHolder(view)
            }
            else -> null
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeroViewHolder -> presenter.onBindViewHolder(holder, position)
            is BasicViewHolder -> presenter.onBindViewHolder(holder, position)
        }
    }

    override fun getItemCount(): Int {
        return presenter.getItemCount()
    }

}