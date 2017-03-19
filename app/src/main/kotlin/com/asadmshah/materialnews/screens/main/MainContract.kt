package com.asadmshah.materialnews.screens.main

import android.os.Bundle
import com.asadmshah.materialnews.models.Article
import com.asadmshah.materialnews.models.Publisher

interface MainContract {

    interface View {

        fun setToolbarTitle(title: String)

        fun setAdapter()
    }

    interface Presenter {

        fun onCreate(savedInstanceState: Bundle?)

        fun onSaveInstanceState(outState: Bundle)

        fun onDestroy()

        fun getPublishersCount(): Int

        fun getPageContent(position: Int): Pair<Publisher, List<Article>>
    }
}