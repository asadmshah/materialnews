package com.asadmshah.materialnews.screens.base

import android.content.Context
import com.asadmshah.materialnews.scopes.PresenterScope
import dagger.Module
import dagger.Provides

@Module
class PresenterModule (private val activity: BaseActivity) {

    @Provides
    @PresenterScope
    fun context(): Context {
        return activity
    }

}