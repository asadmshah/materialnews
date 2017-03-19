package com.asadmshah.materialnews.schedulers

import com.asadmshah.materialnews.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class SchedulersModule {

    @Provides
    @ApplicationScope
    internal fun schedulers(): Schedulers {
        return SchedulersImpl()
    }

}