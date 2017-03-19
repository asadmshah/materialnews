package com.asadmshah.materialnews.screens.main

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import com.asadmshah.materialnews.R
import com.asadmshah.materialnews.screens.base.BaseActivity

class MainActivity : BaseActivity(), MainContract.View {

    private val presenter: MainContract.Presenter by lazy { MainPresenter(this, component) }

    private val viewToolbar: Toolbar by lazy { findView<Toolbar>(R.id.toolbar) }
    private val viewPager: ViewPager by lazy { findView<ViewPager>(R.id.view_pager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        presenter.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.onDestroy()
    }

    override fun setToolbarTitle(title: String) {
        viewToolbar.title = title
    }

    override fun setAdapter() {
        viewPager.adapter = PagerAdapter(presenter)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                presenter.onPageSelected(position)
            }
        })
    }
}