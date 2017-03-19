package com.asadmshah.materialnews.screens.base

import android.support.v7.app.AppCompatActivity
import com.asadmshah.materialnews.BaseApplication

abstract class BaseActivity : AppCompatActivity() {

    protected val component: PresenterComponent by lazy {
        DaggerPresenterComponent
                .builder()
                .baseComponent(BaseApplication.getComponent(this))
                .presenterModule(PresenterModule(this))
                .build()
    }

    inline protected fun <reified T> findView(id: Int): T {
        return findViewById(id) as T
    }

}