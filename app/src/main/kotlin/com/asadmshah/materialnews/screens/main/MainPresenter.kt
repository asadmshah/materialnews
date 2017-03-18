package com.asadmshah.materialnews.screens.main

import android.os.Bundle
import com.asadmshah.materialnews.screens.base.BasePresenter

class MainPresenter(private val view: MainContract.View) : BasePresenter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}