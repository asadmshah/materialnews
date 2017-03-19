package com.asadmshah.materialnews.screens.main

import android.os.Bundle
import com.asadmshah.materialnews.R
import com.asadmshah.materialnews.screens.base.BaseActivity

class MainActivity : BaseActivity(), MainContract.View {

    private val presenter: MainContract.Presenter by lazy {
        MainPresenter(this, component)
    }

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

}