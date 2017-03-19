package com.asadmshah.materialnews.screens.page

import com.asadmshah.materialnews.models.Article
import com.asadmshah.materialnews.models.Publisher
import com.asadmshah.materialnews.screens.base.PresenterComponent

class PagePresenter(private val view: PageContract.View,
                    private val component: PresenterComponent,
                    private val publisher: Publisher,
                    private val articles: List<Article>) : PageContract.Presenter {

    override fun onCreate() {
        view.setHeroImage(articles[0].imageUrl)
        view.setAdapter()
    }

    override fun onDestroy() {

    }

    override fun getItemViewType(position: Int): Class<*> {
        return if (position == 0) HeroViewHolder::class.java else BasicViewHolder::class.java
    }

    override fun onBindViewHolder(viewHolder: HeroViewHolder, position: Int) {
        viewHolder.setPublisherTitle(publisher.name)
        viewHolder.setArticleTitle(articles[position].title)
        viewHolder.setArticleDate(articles[position].date)
    }

    override fun onBindViewHolder(viewHolder: BasicViewHolder, position: Int) {
        viewHolder.setImage(articles[position].imageUrl)
        viewHolder.setTitle(articles[position].title)
        viewHolder.setDate(articles[position].date)
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}