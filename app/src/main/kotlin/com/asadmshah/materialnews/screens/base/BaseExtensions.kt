package com.asadmshah.materialnews.screens.base

import android.view.View
import com.asadmshah.materialnews.BaseApplication

fun View.getComponent(): PresenterComponent {
    return DaggerPresenterComponent
            .builder()
            .baseComponent(BaseApplication.getComponent(context))
            .presenterModule(PresenterModule(context))
            .build()
}

inline fun <reified T> View.findView(id: Int): T {
    return findViewById(id) as T
}