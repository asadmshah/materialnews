package com.asadmshah.materialnews.news

import com.asadmshah.materialnews.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

@Module
class NewsModule {

    @Provides
    @ApplicationScope
    internal fun okhttp(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @ApplicationScope
    internal fun retrofit(okHttpClient: OkHttpClient): Endpoints {
        return Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl("https://newsapi.org/v1/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(Endpoints::class.java)
    }

    @Provides
    @ApplicationScope
    internal fun newsClient(newsClient: NewsClientImpl): NewsClient {
        return newsClient
    }

}