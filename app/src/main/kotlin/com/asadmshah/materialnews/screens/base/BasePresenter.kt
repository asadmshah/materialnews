package com.asadmshah.materialnews.screens.base

import android.os.Bundle

interface BasePresenter {

    fun onCreate(savedInstanceState: Bundle?) {

    }

    fun onSaveInstanceState(outState: Bundle) {

    }

    fun onDestroy() {

    }

}