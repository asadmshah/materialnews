package com.asadmshah.materialnews

import android.app.Application
import android.content.Context
import timber.log.Timber

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

        prepareLogger()
    }

    private fun prepareLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}