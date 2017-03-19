package com.asadmshah.materialnews.screens.main

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import com.asadmshah.materialnews.screens.page.PageView

class PagerAdapter(private val presenter: MainContract.Presenter) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val content = presenter.getPageContent(position)
        val view = PageView(container.context, content.first, content.second)
        view.layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        view.tag = "position-$position"
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return presenter.getPublishersCount()
    }

}