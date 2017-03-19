package com.asadmshah.materialnews

import android.app.Application
import com.asadmshah.materialnews.news.NewsClient
import com.asadmshah.materialnews.news.NewsModule
import com.asadmshah.materialnews.schedulers.Schedulers
import com.asadmshah.materialnews.schedulers.SchedulersModule
import com.asadmshah.materialnews.scopes.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(
        modules = arrayOf(
                BaseModule::class,
                NewsModule::class,
                SchedulersModule::class
        )
)
interface BaseComponent {
    fun application(): Application
    fun newsClient(): NewsClient
    fun schedulers(): Schedulers
}