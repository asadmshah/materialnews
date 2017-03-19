package com.asadmshah.materialnews.news

import com.asadmshah.materialnews.models.Article
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
internal class Response {

    @JsonProperty("articles")
    @JsonIgnoreProperties(ignoreUnknown = true)
    lateinit var articles: List<Article>

}