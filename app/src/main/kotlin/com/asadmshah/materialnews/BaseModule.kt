package com.asadmshah.materialnews

import android.app.Application
import com.asadmshah.materialnews.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
internal class BaseModule (private val application: BaseApplication) {

    @Provides
    @ApplicationScope
    fun application(): Application {
        return application
    }

}