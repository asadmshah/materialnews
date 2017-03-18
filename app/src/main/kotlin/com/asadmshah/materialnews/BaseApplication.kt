package com.asadmshah.materialnews

import android.app.Application
import android.content.Context

class BaseApplication : Application() {

    companion object {
        fun getComponent(context: Context): BaseComponent {
            return (context.applicationContext as BaseApplication).component
        }
    }

    private val component: BaseComponent by lazy {
        DaggerBaseComponent.builder().baseModule(BaseModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
    }

}