package com.asadmshah.materialnews.screens.base

import android.content.Context
import com.asadmshah.materialnews.BaseComponent
import com.asadmshah.materialnews.scopes.PresenterScope
import dagger.Component

@PresenterScope
@Component(dependencies = arrayOf(BaseComponent::class), modules = arrayOf(PresenterModule::class))
interface PresenterComponent {
    fun context(): Context
}