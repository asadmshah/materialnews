package com.asadmshah.materialnews.screens.page

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.asadmshah.materialnews.R
import com.asadmshah.materialnews.models.Article
import com.asadmshah.materialnews.models.Publisher
import com.asadmshah.materialnews.screens.base.findView
import com.asadmshah.materialnews.screens.base.getComponent
import com.asadmshah.materialnews.widgets.BlurView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.lang.Exception

class PageView(context: Context, publisher: Publisher, articles: List<Article>) : FrameLayout(context), PageContract.View {

    val viewImage by lazy { findView<ImageView>(R.id.hero_image) }
    val viewBlur by lazy { findView<BlurView>(R.id.hero_blur) }
    val viewList by lazy { findView<RecyclerView>(R.id.recycler_view) }
    val viewIcon by lazy { findView<ImageView>(R.id.up_arrow) }
    val viewLazy by lazy { findView<View>(R.id.lazy_white) }
    val viewToolbar by lazy { findView<Toolbar>(R.id.toolbar) }

    val presenter: PageContract.Presenter
    var adapter: Adapter? = null

    init {
        View.inflate(getContext(), R.layout.view_page, this)

        viewList.layoutManager = LinearLayoutManager(context)
        viewList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            var position = 0f

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                position += dy

                val y = Math.min(position / height, 1f)

                viewImage.scaleX = 1+y*0.5f
                viewImage.scaleY = 1+y*0.5f

                viewIcon.alpha = 1-y*3
                viewIcon.translationY = -position

                viewBlur.alpha = y*4
                viewBlur.scaleX = 1+y*0.5f
                viewBlur.scaleY = 1+y*0.5f

                viewLazy.alpha = y*1.25f

                viewToolbar.alpha = y*1.25f
            }
        })

        presenter = PagePresenter(this, getComponent(), publisher, articles)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        presenter.onCreate()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        presenter.onDestroy()
    }

    override fun setHeroImage(url: String) {
        Glide.with(context)
                .load(url)
                .crossFade()
                .listener(object : RequestListener<String, GlideDrawable> {
                    override fun onResourceReady(resource: GlideDrawable?, model: String?, target: Target<GlideDrawable>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        postDelayed({ viewBlur.setBlurSource(viewImage, 10, 10f) }, 17)
                        return false
                    }

                    override fun onException(e: Exception?, model: String?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }
                })
                .into(viewImage)
    }

    override fun setAdapter() {
        adapter = Adapter(presenter)
        viewList.adapter = adapter
    }

    override fun notifyDataSetChanged() {
        adapter?.notifyDataSetChanged()
    }
}