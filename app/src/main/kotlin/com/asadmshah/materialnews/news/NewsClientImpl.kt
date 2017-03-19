package com.asadmshah.materialnews.news

import com.asadmshah.materialnews.BuildConfig
import com.asadmshah.materialnews.models.Article
import com.asadmshah.materialnews.models.Publisher
import io.reactivex.Observable
import javax.inject.Inject

internal class NewsClientImpl @Inject constructor(private val endpoints: Endpoints) : NewsClient {

    override fun get(publisher: Publisher): Observable<Pair<Publisher, List<Article>>> {
        return endpoints
                .articles(publisher.code, BuildConfig.NEWS_KEY)
                .map { publisher to it.articles }
    }

    override fun get(vararg publishers: Publisher): Observable<Pair<Publisher, List<Article>>> {
        return Observable
                .defer { Observable.fromArray(*publishers) }
                .flatMap { get(it) }
    }
}