package com.asadmshah.materialnews

import android.app.Application
import com.asadmshah.materialnews.scopes.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = arrayOf(BaseModule::class))
interface BaseComponent {
    fun application(): Application
}