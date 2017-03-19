package com.asadmshah.materialnews.news

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

internal interface Endpoints {

    @GET("articles")
    fun articles(@Query("source") source: String, @Query("apiKey") key: String): Observable<Response>

}