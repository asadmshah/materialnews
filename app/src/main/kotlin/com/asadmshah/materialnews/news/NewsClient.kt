package com.asadmshah.materialnews.news

import com.asadmshah.materialnews.models.Article
import com.asadmshah.materialnews.models.Publisher
import io.reactivex.Observable

interface NewsClient {

    fun get(publisher: Publisher): Observable<Pair<Publisher, List<Article>>>

    fun get(): Observable<Pair<Publisher, List<Article>>>

}