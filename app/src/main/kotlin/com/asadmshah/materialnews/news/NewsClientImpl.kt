package com.asadmshah.materialnews.news

import com.asadmshah.materialnews.BuildConfig
import com.asadmshah.materialnews.models.Article
import com.asadmshah.materialnews.models.Publisher
import com.asadmshah.materialnews.storage.Storage
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.reactivex.Observable
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class NewsClientImpl @Inject constructor(private val endpoints: Endpoints, private val storage: Storage) : NewsClient {

    companion object {
        private val JSON_MAPPER = jacksonObjectMapper()
        private val JSON_WRITER = JSON_MAPPER.writerFor(Response::class.java)
        private val JSON_READER = JSON_MAPPER.readerFor(Response::class.java)

        private val PUBLISHERS = arrayOf(
                Publisher("Ars Technica", "ars-technica", 0),
                Publisher("BBC News", "bbc-news", 0),
                Publisher("BuzzFeed", "buzzfeed", 0),
                Publisher("ESPN", "espn", 0)
        )
    }

    override fun get(publisher: Publisher): Observable<Pair<Publisher, List<Article>>> {
        if (isRecentlyCached(publisher)) {
            return getCached(publisher).map { publisher to it.articles }
        }
        return getFresh(publisher)
                .onErrorResumeNext { throwable: Throwable ->
                    if (throwable is IOException && storage.file(publisher.code).exists()) {
                        Timber.d("Using cached response for ${publisher.code}")
                        val response: Response = storage.reader(publisher.code).use { JSON_READER.readValue(it) }
                        Observable.just(response)
                    } else {
                        Observable.error(throwable)
                    }
                }
                .map { publisher to it.articles }
    }

    override fun get(): Observable<Pair<Publisher, List<Article>>> {
        return Observable
                .defer { Observable.fromArray(*PUBLISHERS) }
                .flatMap { get(it) }
    }

    fun isRecentlyCached(publisher: Publisher): Boolean {
        val file = storage.file(publisher.code)
        if (file.exists()) {
            val a = TimeUnit.MILLISECONDS.toHours(file.lastModified())
            val b = TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis())
            return b - a < 2
        }
        return false
    }

    fun getCached(publisher: Publisher): Observable<Response> {
        return Observable
                .fromCallable { storage.reader(publisher.code).use { JSON_READER.readValue<Response>(it) } }
    }

    fun getFresh(publisher: Publisher): Observable<Response> {
        return endpoints
                .articles(publisher.code, BuildConfig.NEWS_KEY)
                .doOnNext { response ->
                    Timber.d("Caching response for ${publisher.code}")
                    storage.writer(publisher.code).use {
                        JSON_WRITER.writeValue(it, response)
                    }
                }
    }
}