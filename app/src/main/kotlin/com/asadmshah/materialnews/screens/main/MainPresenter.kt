package com.asadmshah.materialnews.screens.main

import android.os.Bundle
import android.util.Log
import com.asadmshah.materialnews.models.Article
import com.asadmshah.materialnews.models.Publisher
import com.asadmshah.materialnews.news.NewsClient
import com.asadmshah.materialnews.schedulers.Schedulers
import com.asadmshah.materialnews.screens.base.PresenterComponent
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

internal class MainPresenter(private val view: MainContract.View, private val component: PresenterComponent) : MainContract.Presenter {

    private val publishers = arrayOf(
            Publisher("Ars Technica", "ars-technica", 0),
            Publisher("BBC News", "bbc-news", 0)
    )

    private val client: NewsClient by lazy { component.newsClient() }
    private val schedulers: Schedulers by lazy { component.schedulers() }

    private var newsDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        requestContent()
    }

    override fun onSaveInstanceState(outState: Bundle) {

    }

    override fun onDestroy() {
        newsDisposable?.dispose()
    }

    fun requestContent() {
        client.get(*publishers)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .toList()
                .subscribe(object : SingleObserver<MutableList<Pair<Publisher, List<Article>>>> {
                    override fun onSuccess(response: MutableList<Pair<Publisher, List<Article>>>) {
                        Log.d("MainPresenter", "Received ${response.size}")
                    }

                    override fun onError(e: Throwable) {
                        newsDisposable = null

                        e.printStackTrace()
                    }

                    override fun onSubscribe(d: Disposable) {
                        newsDisposable = d
                    }
                })
    }

}