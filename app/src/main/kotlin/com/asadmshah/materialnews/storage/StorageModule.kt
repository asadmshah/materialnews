package com.asadmshah.materialnews.storage

import com.asadmshah.materialnews.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
internal class StorageModule {

    @Provides
    @ApplicationScope
    fun storage(storage: StorageImpl): Storage {
        return storage
    }

}